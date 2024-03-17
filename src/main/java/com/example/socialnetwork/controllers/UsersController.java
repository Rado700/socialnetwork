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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@Tag(name= "User Controller", description="Описание контрорллера пользователя")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    PostService postService;

    @GetMapping("/user/comments/{user_id}")
    public ResponseEntity<List<UserIdCommentDTO>> posts (@PathVariable Integer user_id){
        //TODO : возвращает комментарии пользователя(Users)
        Set<Commentarie> comment = userService.getUserComment(user_id);
        List<UserIdCommentDTO> userIdComments = new ArrayList<>();
        for (Commentarie comments : comment) {
            userIdComments.add(new UserIdCommentDTO(comments.getId(), comments.getPost(),comments.getText()));
        }
        return new ResponseEntity<>(userIdComments,HttpStatus.OK);

    }

    //----------------------------------------------------------------------------------------------------
    @Operation(summary = "возвращает значение лайка",description = "возвращает лайк по определенному id польвотеля" +
            " с полной иформацией пользователя и поста")
    @GetMapping("/user/likes/{userId}")
    public ResponseEntity<List<LikeIdPostDto>> likes (@PathVariable Integer userId){
        /**
         * возвращает лайк по определенному id польвотеля
         * с полной иформацией пользователя и поста
         */
        Set<Likes> likes = likeService.getLikes(userId);
        List<LikeIdPostDto> likesDtoList = new ArrayList<>();
        for (Likes like : likes){
            likesDtoList.add(new LikeIdPostDto(like.getPost(),like.getUsers()));
        }
        return new ResponseEntity<>(likesDtoList,HttpStatus.OK);
    }
    //----------------------------------------------------------------------------------------------------------
    @DeleteMapping("users/{user_id}")
    public ResponseEntity<Users>deleteUser(@PathVariable Integer user_id){
        //TODO: Удалаяет пользователя по ИД
        Users users = userService.deleteUser(user_id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    //----------------------------------------------------------------------------------------
    @GetMapping("/users")
    public ResponseEntity<List<Users>> name() {
        //TODO:Возвращает всех пользователей
        List<Users> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    //------------------------------------------------------------------------------------------------
        @PostMapping("/users")
        public ResponseEntity<Object>addUsers(@RequestBody UsersDTO users) {
        //TODO:Добавляет пользовтеля
            try {
                Users user = userService.addUser(users.getName(), users.getSurname(), users.getUrl(), users.getPremium());
                return new ResponseEntity<>(user, HttpStatus.OK);

            }catch (DataIntegrityViolationException s){
                return new ResponseEntity<>(s,HttpStatus.CONFLICT);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    @GetMapping("/user/post/{user_id}")
    public ResponseEntity<UserIdPostDTO> getUsersPost(@PathVariable Integer user_id){
        //TODO:
//        Users users = userService.getUser(user_id);
//        Post post = postService.getPost(user_id);
        Set<Post> post = userService.getUserPost(user_id);
        Users user = userService.getUser(user_id);
        UserIdPostDTO userIdPostDTO = new UserIdPostDTO(post,user );
        return new ResponseEntity<>(userIdPostDTO,HttpStatus.OK);
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

    @GetMapping("/getUsersPosts")
    public Map<Integer, Map<String, Object>> getUserPost(Integer userId) {

        UsersDB usersDB = new UsersDB();

        List<Map<String, Object>> getUsersPosts = usersDB.getUsersPost(userId);

        Map<Integer, Map<String, Object>> newMap = new HashMap<>();

        for (int i = 0; i < getUsersPosts.size(); i++) {

            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = getUsersPosts.get(i);
            map.put(userId + " id", map1);

            newMap.put(i, map);
        }
        return newMap;
    }

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
