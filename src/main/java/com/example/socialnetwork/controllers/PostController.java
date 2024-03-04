package com.example.socialnetwork.controllers;

import com.example.socialnetwork.services.PostDB;
import com.example.socialnetwork.dto.Comment;
import com.example.socialnetwork.dto.Likes;
import com.example.socialnetwork.dto.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PostController {

    @PostMapping("/posts")
    public ResponseEntity<String>addPosts(@RequestBody Post post){
        PostDB postDB = new PostDB();
        postDB.addPosts(post.getContento(), post.getDate(),post.getTime(), post.getUsers_id());
        return new ResponseEntity<>("Успешно", HttpStatus.OK);
    }

    @PostMapping("/likes")
    public ResponseEntity<String>addLikes(@RequestBody Likes likes){
        PostDB postDB = new PostDB();
        boolean isLikeExist = postDB.isLikeExist(likes.getUser_id(), likes.getPost_id());

        if (isLikeExist){
            return new ResponseEntity<>("Лайк уже есть",HttpStatus.BAD_REQUEST);
        }
        postDB.addLike(likes.getUser_id(), likes.getPost_id());
        return new ResponseEntity<>("like Добавлен",HttpStatus.OK);
    }


    @PostMapping("/commentarie")
    public ResponseEntity<String>addComment (@RequestBody Comment comment){

        PostDB postDB = new PostDB();

        if (comment.getText().isEmpty()){
            return new ResponseEntity<>("Нету текста",HttpStatus.BAD_REQUEST);
        }

        postDB.addComment(comment.getPost_id(),comment.getUser_id(),comment.getText());
        return new ResponseEntity<>("Комментарий добавлен",HttpStatus.OK);
    }

    @DeleteMapping("/deleteLikes")
    public ResponseEntity<Object>deleteLike(@RequestBody Likes likes){

        PostDB postDB = new PostDB();
        boolean isLikeExist = postDB.isLikeExist(likes.getUser_id(), likes.getPost_id());

        if (!isLikeExist){
            return new ResponseEntity<>("Лайка не существует",HttpStatus.BAD_REQUEST);
        }

        Map<String, Object>like = postDB.getLike(likes.getUser_id(), likes.getPost_id());
        postDB.deleteLike(likes.getUser_id(), likes.getPost_id());

        return new ResponseEntity<>(like,HttpStatus.OK);
    }

}
