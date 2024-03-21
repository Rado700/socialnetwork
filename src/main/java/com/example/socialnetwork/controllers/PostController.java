package com.example.socialnetwork.controllers;

import com.example.socialnetwork.dto.LikesDTO;
import com.example.socialnetwork.dto.PostDTO;
import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.services.*;
import com.example.socialnetwork.dto.CommentDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@OpenAPIDefinition(
        info = @Info(
                title = "Социальная сеть",
                version = "1.0",
                description = "Описание",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
        )
)
//@OpenAPIDefinition(
//        info = @Info(
//                title = "the title",
//                version = "0.0",
//                description = "My API",
//                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
//                contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
//        ),
//        tags = {
//                @Tag(name = "Tag 1", description = "desc 1", externalDocs = @ExternalDocumentation(description = "docs desc")),
//                @Tag(name = "Tag 2", description = "desc 2", externalDocs = @ExternalDocumentation(description = "docs desc 2")),
//                @Tag(name = "Tag 3")
//        },
//        externalDocs = @ExternalDocumentation(description = "definition docs desc"),
//        security = {
//                @SecurityRequirement(name = "req 1", scopes = {"a", "b"}),
//                @SecurityRequirement(name = "req 2", scopes = {"b", "c"})
//        },
//        servers = {
//                @Server(
//                        description = "server 1",
//                        url = "http://foo",
//                        variables = {
//                                @ServerVariable(name = "var1", description = "var 1", defaultValue = "1", allowableValues = {"1", "2"}),
//                                @ServerVariable(name = "var2", description = "var 2", defaultValue = "1", allowableValues = {"1", "2"})
//                        })
//        }
//)
@RestController
@Tag(name = "Post controller", description = "Вся информация о посте пользователя")
public class PostController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;

    @Operation(summary = "Возвращает все посты")
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> post() {
        /**
         * Возвращает информацию о всех постах
         */
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

//    @Operation(summary = "Возвращает пост пользователя по id")
//    @GetMapping("/post/{id}")
//    public ResponseEntity<Set<Post>> userPost(Integer id) {
//        /**
//         * возвращает пост пользователя по id
//         */
//        Set<Post> post = userService.getUserPost(id);
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }

    @Operation(summary = "Удаляет пост по id")
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Integer id) throws Exception {
//        try {
            Post post = postService.deletePosts(id);
            return new ResponseEntity<>(post,HttpStatus.OK);
//        }catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }

    }
    @PostMapping("/post")
    public ResponseEntity<Post>addPosts(@RequestBody PostDTO postDTO){
        Users user = userService.getUser(postDTO.getUsers_id());
        Post newPost = postService.addPost(postDTO.getContento(),postDTO.getDate(),postDTO.getTime(),user);
        return new ResponseEntity<>(newPost, HttpStatus.OK);

    }

    //    @PostMapping("/posts")
//    public ResponseEntity<String>addPosts(@RequestBody Post post){
//        PostDB postDB = new PostDB();
//        postDB.addPosts(post.getContento(), post.getDate(),post.getTime(), post.getUsers_id());
//        return new ResponseEntity<>("Успешно", HttpStatus.OK);
//    }
//--------------------------------------------------------------------------------------------
    @Operation(summary = "Возвращает все комментария")
    @GetMapping("/commentarie")
    public ResponseEntity<List<Commentarie>> getCommentarie() {
        /**
         * Возвращает все комментарий
         */
        List<Commentarie> commentaries = commentService.getCommentaries();
        return new ResponseEntity<>(commentaries, HttpStatus.OK);
    }

    @Operation(summary = "Добавляет комментарий", description = "после добавления комментария возваращает информацию о посте," +
            "пользователе и текст комментария")
    @PostMapping("/commentarie")
    public ResponseEntity<Commentarie> addComment(@RequestBody CommentDTO comment) {
        /**
         * После добавления возвращает Json с добавленным комментарием(id, user(полная инфа),post(полная инфа))
         */
        Users user = userService.getUser(comment.getUser_id());
        Post post = postService.getPost(comment.getPost_id());
        String text = comment.getText();
        Commentarie commentarie = commentService.addcomment(user, post, text);
        return new ResponseEntity<>(commentarie, HttpStatus.OK);
    }

    @Operation(summary = "Удалает комментарий", description = "удалает коменнтарий по id и возвращает удаленный комментарий")
    @DeleteMapping("/commentarie/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Integer commentId) {
        /**
         * После удаления комментария возвращает Json с удаленным комментом
         */
        try {
            Commentarie commentarie = commentService.deleteComment(commentId);
            return new ResponseEntity<>(commentarie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//-------------------------------------------------------------------------------------------------

    @Operation(summary = "Возвращает все лайки")
    @GetMapping("/likes")
    public ResponseEntity<List<Likes>> like() {
        /**
         * Возвращает все лайки
         */
        List<Likes> likes = likeService.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @Operation(summary = "Добаляет лайк", description = "после добавления лайка возваращают полную информацию о посте и пользователе")
    @PostMapping("/likes")
    public ResponseEntity<Likes> addLikes(@RequestBody LikesDTO likes) {
        /**
         * После добавления возвращает json с добавленным лайком (id,post(полная инфа),user(полная инфа))
         */
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


    @Operation(summary = "Удаление лайка по id", description = "после удаления возвращает полную информацию о пользователе и " +
            "посте")
    @DeleteMapping("/likes/{likeId}")
    public ResponseEntity<Object> deleteUserLike(@PathVariable Integer likeId) {
        /**
         * после удаления лайка возвращает json c удаленным лайком (user(полная инфа)post(полная инфа))
         */
        try {
            Likes like = likeService.deleteLike(likeId);
            return new ResponseEntity<>(like, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
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
