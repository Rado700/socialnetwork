package com.example.socialnetwork.controllers;

import com.example.socialnetwork.dto.LikeIdPostDto;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.services.UserService;
import com.example.socialnetwork.services.UsersDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsersController {
    @Autowired
    UserService userService;
    @GetMapping("/user/likes/{userId}")
    public ResponseEntity<List<LikeIdPostDto>> likes (@PathVariable Integer userId){
        Set<Likes> likes = userService.getUserLikes(userId);
        List<LikeIdPostDto> likesDtoList = new ArrayList<>();

        for (Likes like : likes){
            likesDtoList.add(new LikeIdPostDto(like.getId(),like.getPost()));
        }
        return new ResponseEntity<>(likesDtoList,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> name() {
        List<Users> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
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
    }

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


    @PostMapping("/addUsers")
    public ResponseEntity<String>addUsers(@RequestBody com.example.socialnetwork.dto.Users users){

        UsersDB usersDB = new UsersDB();
        usersDB.addUsers(users.getName(),users.getSurname(),users.getUrl(),users.getPremium());

        return new ResponseEntity<>("very good",HttpStatus.OK);

    }
}
//1. Добавление комментария с проверкой,
//        что текст комментария не пустая строка,
//        в противном случае возвращать ошибку 400 с информацией,
//        что комментарий пустой и не добавлять в базу данных
//
//        2. Добавление лайка, если этим пользователем в данном посте не стоял лайк раннее,
//        в противном случае возвращать ошибку [номер_ошибки] с информацией,
//        что лайк уже имеется на данном посте от данного пользователя

