package com.example.socialnetwork.controllers;

import com.example.socialnetwork.dto.LikesDTO;
import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.services.*;
import com.example.socialnetwork.dto.CommentDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@OpenAPIDefinition(
        info = @Info(
                title = "Социальная сеть",
                version = "1.0",
                description = "Описание",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
        )
)
@RestController
public class PostController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> post() {
        //TODO:Вовращает все посты
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Post>> userPost(Integer id) {
        List<Post>post = postService.getPosts(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    //    @PostMapping("/posts")
//    public ResponseEntity<String>addPosts(@RequestBody Post post){
//        PostDB postDB = new PostDB();
//        postDB.addPosts(post.getContento(), post.getDate(),post.getTime(), post.getUsers_id());
//        return new ResponseEntity<>("Успешно", HttpStatus.OK);
//    }
//--------------------------------------------------------------------------------------------
    @GetMapping("/commentarie")
    public ResponseEntity<List<Commentarie>> getCommentarie() {
        //TODO:Возвращает все коменнтарий
        List<Commentarie> commentaries = commentService.getCommentaries();
        return new ResponseEntity<>(commentaries, HttpStatus.OK);
    }

    @PostMapping("/commentarie")
    public ResponseEntity<Commentarie> addComment(@RequestBody CommentDTO comment) {
        //TODO: после добавления возвращает Json с добавленным комментарием (id, user(полная инфа),post(полная инфа))
        Users user = userService.getUser(comment.getUser_id());
        Post post = postService.getPost(comment.getPost_id());
        String text = comment.getText();
        Commentarie commentarie = commentService.addcomment(user, post, text);
        return new ResponseEntity<>(commentarie, HttpStatus.OK);
    }

    @DeleteMapping("/commentarie/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Integer commentId) {
        //TODO: После удаления комментария возвращает json  с удаленным комментом
        try {
            Commentarie commentarie = commentService.deleteComment(commentId);
            return new ResponseEntity<>(commentarie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//-------------------------------------------------------------------------------------------------

    @GetMapping("/likes")
    public ResponseEntity<List<Likes>> like() {
        //TODO:Вовращает все лайки
        List<Likes> likes = likeService.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @PostMapping("/likes")
    public ResponseEntity<Likes> addLikes(@RequestBody LikesDTO likes) {
        //TODO: после добавления возвращать json с добавленным лайком (id, post(полная инфа), user(полная инфа)
        Users user = userService.getUser(likes.getUser_id());
        Post post = postService.getPost(likes.getPost_id());
        Likes like = likeService.addLike(user, post);
        return new ResponseEntity<>(like, HttpStatus.OK);
//        PostDB postDB = new PostDB();
//        boolean isLikeExist = postDB.isLikeExist(likes.getUser_id(), likes.getPost_id());
//
//        if (isLikeExist){
//            return new ResponseEntity<>("Лайк уже есть",HttpStatus.BAD_REQUEST);
//        }
//        postDB.addLike(likes.getUser_id(), likes.getPost_id());
//        return new ResponseEntity<>("like Добавлен",HttpStatus.OK);
    }

//    @DeleteMapping("/likes")
//    public ResponseEntity<Likes> deleteLikes(@RequestBody LikesDTO likesDTO) {
//        //TODO:после удаления лайка возвращает json c удаленным лайком (user(полная инфа)post(полная инфа))
//        Users user = userService.getUser(likesDTO.getUser_id());
//        Post post = postService.getPost(likesDTO.getPost_id());
//        Likes likes = new Likes(user, post);
//        return new ResponseEntity<>(likes, HttpStatus.OK);
//    }

    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<Object>deleteUserLike(@PathVariable Integer likeId){
        try {
            Likes like = likeService.deleteLike(likeId);
            return new ResponseEntity<>(like,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

//    @DeleteMapping("/deleteLikes")
//    public ResponseEntity<Object>deleteLike(@RequestBody LikesDTO likes){
//
//        PostDB postDB = new PostDB();
//        boolean isLikeExist = postDB.isLikeExist(likes.getUser_id(), likes.getPost_id());
//
//        if (!isLikeExist){
//            return new ResponseEntity<>("Лайка не существует",HttpStatus.BAD_REQUEST);
//        }
//
//        Map<String, Object>like = postDB.getLike(likes.getUser_id(), likes.getPost_id());
//        postDB.deleteLike(likes.getUser_id(), likes.getPost_id());
//
//        return new ResponseEntity<>(like,HttpStatus.OK);
//    }


//    @DeleteMapping("/deleteComment")
//    public ResponseEntity<Object>deleteComment(@RequestBody CommentDTO comment){
//        PostDB postDB = new PostDB();
//
//        boolean isCommentExist = postDB.isCommentExist(comment.getUser_id(),comment.getPost_id());
//
//        if (!isCommentExist){
//            return new ResponseEntity<>("Комментария не существует",HttpStatus.BAD_REQUEST);
//        }
//        postDB.deleteComment(comment.getUser_id(),comment.getPost_id());
//
//        List<Map<String,Object>> delete = postDB.getComments(comment.getUser_id());
//
//        return new ResponseEntity<>(delete,HttpStatus.OK);
//
//    }

}
