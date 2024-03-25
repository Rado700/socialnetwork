package com.example.socialnetwork.services;

import com.example.socialnetwork.models.*;
import com.example.socialnetwork.repository.AuthorizationRepository;
import com.example.socialnetwork.repository.UserRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users getUser(Integer user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

    public Users addUser(String name, String surname, String url, Boolean premium) throws PSQLException {
        Users users = new Users(name, surname, url, premium);
        return userRepository.save(users);
    }

    public Users deleteUser(Integer id) throws Exception {
        Users user = getUser(id);
        if (user == null) {
            throw new Exception("нету такого пользователя");
        }
        userRepository.delete(user);
        return user;
    }

    public Set<Commentarie> getUserComment(Integer id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(Users::getCommentaries).orElse(null);
    }

    public Set<Likes> getLikes(Integer id) {
        Optional<Users> like = userRepository.findById(id);
        return like.map(Users::getLikes).orElse(null);
    }

    public Set<Post> getUserPost(Integer id) {
        Optional<Users> users = userRepository.findById(id);
        return users.map(Users::getPosts).orElse(null);
    }

//    public String encoderPassword(String newPassword){
//        PasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
//        passwordEncoder1.encode(newPassword);
//        return String.valueOf(passwordEncoder1);
//    }
    public Users getUserByLoginPassword(String user, String password) {
        System.out.println(password);
        String passwordEncoded = passwordEncoder.encode(password);
        System.out.println(passwordEncoded);
        Authorisation authorisation = authorizationRepository.findByLogin(user);

        if (authorisation == null || !passwordEncoder.matches(password,authorisation.getPassword())){
            return null;
        }
        return authorisation.getUsers();
    }

    public Users addNewRegistry(String login, String password, Users userId) throws Exception {
        Authorisation authorisation = authorizationRepository.findByLoginAndPassword(login, password);
        if (authorisation != null) {
            throw new Exception("такой пользователь уже есть");
        } else {
            String passwordEncoded = passwordEncoder.encode(password);
            Authorisation authorisation1 = new Authorisation(login, passwordEncoded, userId);
            return authorizationRepository.save(authorisation1).getUsers();
        }

    }
    public Authorisation getAccount(String login){
        return authorizationRepository.findByLogin(login);
    }


}

