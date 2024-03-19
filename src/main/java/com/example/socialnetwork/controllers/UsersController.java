package com.example.socialnetwork.controllers;

import com.example.socialnetwork.dto.LikeIdPostDto;
import com.example.socialnetwork.dto.UserIdCommentDTO;
import com.example.socialnetwork.dto.UserIdPostDTO;
import com.example.socialnetwork.dto.UsersDTO;
import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Tag(name = "User Controller", description = "Описание контроллера пользователя")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    PostService postService;

    @Operation(summary = "Возвращает комментарий пользователя",description = "возвращает коменнтарий полтзователя по его id")
    @GetMapping("/user/comments/{user_id}")
    public ResponseEntity<List<UserIdCommentDTO>> posts(@PathVariable Integer user_id) {
        /**
         * Возвращает комментарий пользователя по id
         */
        Set<Commentarie> comment = userService.getUserComment(user_id);
        List<UserIdCommentDTO> userIdComments = new ArrayList<>();
        for (Commentarie comments : comment) {
            userIdComments.add(new UserIdCommentDTO(comments.getId(), comments.getPost(), comments.getText()));
        }
        return new ResponseEntity<>(userIdComments, HttpStatus.OK);

    }

    //----------------------------------------------------------------------------------------------------
    @Operation(summary = "Возвращает лайк пользователя", description = "возвращает лайк по определенному id польвотеля" +
            " с полной иформацией пользователя и поста")
    @GetMapping("/user/likes/{userId}")
    public ResponseEntity<List<LikeIdPostDto>> likes(@PathVariable Integer userId) {
        /**
         * возвращает лайк по определенному id пользователя
         * с полной информацией пользователя и поста
         */
        Set<Likes> likes = likeService.getLikes(userId);
        List<LikeIdPostDto> likesDtoList = new ArrayList<>();
        for (Likes like : likes) {
            likesDtoList.add(new LikeIdPostDto(like.getPost(), like.getUsers()));
        }
        return new ResponseEntity<>(likesDtoList, HttpStatus.OK);
    }

    //----------------------------------------------------------------------------------------------------------
    @Operation(summary = "Удаление пользователя", description = "удаляет пользователя по определенному id")
    @DeleteMapping("users/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer user_id) {
        /**
         * Удаляет пользователя по id
         */
        try {

            Users users = userService.deleteUser(user_id);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //----------------------------------------------------------------------------------------
    @Operation(summary = "Возвращает всех пользователей")
    @GetMapping("/users")
//    @PreAuthorize("USER")
    public ResponseEntity<List<Users>> name() {
        /**
         * Возвращает всех пользователей
         */
        List<Users> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------
    @Operation(summary = "Добавляет пользователя")
    @PostMapping("/users")
    public ResponseEntity<Object> addUsers(@RequestBody UsersDTO users) {
        /**
         * Добавляет пользователя
         */
        try {
            Users user = userService.addUser(users.getName(), users.getSurname(), users.getUrl(), users.getPremium());
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (DataIntegrityViolationException s) {
            return new ResponseEntity<>(s, HttpStatus.CONFLICT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Возваращает информацию о посте пользователя",description = "Возвращает всю информацию о пользователе" +
            "и его посте по id")
    @GetMapping("/user/post/{user_id}")
    public ResponseEntity<UserIdPostDTO> getUsersPost(@PathVariable Integer user_id) {
        /**
         * Возвращает информацию о пользовтеле и его посте по id
         */
        Set<Post> post = userService.getUserPost(user_id);
        Users user = userService.getUser(user_id);
        UserIdPostDTO userIdPostDTO = new UserIdPostDTO(post, user);
        return new ResponseEntity<>(userIdPostDTO, HttpStatus.OK);
    }


//
//        UsersDB usersDB = new UsersDB();
//
//        List<List<String>> users = usersDB.Users();
//
//        Map<String, Map<String, String>> maps = new HashMap<>();
//
//        for (int i = 0; i < users.size(); i++) {
//
//            String name = users.get(i).get(0);
//            String surname = users.get(i).get(1);
//            String url = users.get(i).get(2);
//            Boolean premium = Boolean.valueOf(users.get(i).get(3));
//
//            Map<String, String> maps2 = new HashMap<>();
//            maps2.put("url", url);
//            maps2.put("premium", String.valueOf(premium));
//            maps.put(name + " " + surname, maps2);
//        }
//        return maps;

//    @GetMapping("/getUsersPosts")
//    public Map<Integer, Map<String, Object>> getUserPost(Integer userId) {
//
//        UsersDB usersDB = new UsersDB();
//
//        List<Map<String, Object>> getUsersPosts = usersDB.getUsersPost(userId);
//
//        Map<Integer, Map<String, Object>> newMap = new HashMap<>();
//
//        for (int i = 0; i < getUsersPosts.size(); i++) {
//
//            Map<String, Object> map = new HashMap<>();
//            Map<String, Object> map1 = getUsersPosts.get(i);
//            map.put(userId + " id", map1);
//
//            newMap.put(i, map);
//        }
//        return newMap;
//    }

//    @GetMapping("/getPersonsInfo")
//    public Map<String,Object> getPersonsInfo(Integer id){
//
//        PostDB postDB = new PostDB();
//
//        Map<String, Object> post = postDB.getPost(id);
//        List<String> likes = postDB.getLike(id);
//        List<Map<String, Object>> comments = postDB.getComments(id);
//
//
//        Map<String,Object>getInfo = new HashMap<>();
//
//        getInfo.put("post",post);
//        getInfo.put("likes",likes);
//        getInfo.put("comments",comments);
//
//
//        return getInfo;
//    }


}
