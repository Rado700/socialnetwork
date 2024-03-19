package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.UserRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;

    public List<Users> getUsers(){
        return userRepository.findAll();}

    public Users getUser(Integer user_id){
        return userRepository.findById(user_id).orElse(null);
    }
    public Users addUser(String name, String surname, String url, Boolean premium) throws PSQLException {
        Users users = new Users(name,surname,url,premium);
        return userRepository.save(users);
    }
    public Users deleteUser(Integer id) throws Exception {
        Users user = getUser(id);
        if (user == null){
            throw new Exception("нету такого пользователя");
        }
        userRepository.delete(user);
        return user;
    }

    public Set<Commentarie> getUserComment(Integer id){
        Optional<Users> user = userRepository.findById(id);
        return user.map(Users::getCommentaries).orElse(null);
    }

    public Set<Likes> getLikes(Integer id){
        Optional<Users>like = userRepository.findById(id);
        return like.map(Users::getLikes).orElse(null);
    }
    public Set<Post>getUserPost(Integer id){
        Optional<Users>users = userRepository.findById(id);
        return users.map(Users::getPosts).orElse(null);
    }


}
