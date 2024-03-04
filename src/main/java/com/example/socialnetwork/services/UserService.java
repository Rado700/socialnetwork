package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

}
