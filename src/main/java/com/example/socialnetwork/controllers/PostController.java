package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DB.PostDB;
import com.example.socialnetwork.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PostMapping("/posts")
    public ResponseEntity<String>addPosts(@RequestBody Post post){
        PostDB postDB = new PostDB();
        postDB.addPosts(post.getContento(), post.getDate(),post.getTime(), post.getUsers_id());
        return new ResponseEntity<>("Успешно", HttpStatus.OK);
    }
}
