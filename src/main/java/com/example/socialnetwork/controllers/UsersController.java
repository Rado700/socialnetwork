package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DB.UsersDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@RestController
public class UsersController {

    @GetMapping("/users")
    public Map<String, Map<String, String>> name() {

        UsersDB usersDB = new UsersDB();

        List<List<String>> users = usersDB.Users();

        Map<String, Map<String, String>> maps = new HashMap<>();

        for (int i = 0; i < users.size(); i++) {

            String name = users.get(i).get(0);
            String surname = users.get(i).get(1);
            String url = users.get(i).get(2);
            Boolean premium = Boolean.valueOf(users.get(i).get(3));

            Map<String, String> maps2 = new HashMap<>();
            maps2.put("url", url);
            maps2.put("premium", String.valueOf(premium));
            maps.put(name + " " + surname, maps2);
        }
        return maps;
    }

    @GetMapping("/getUsersPosts")
    public Map<Integer, Map<String, Object>> getUserPost(Integer userId) {

        UsersDB usersDB = new UsersDB();

        List<Map<String, Object>> getUsersPosts = usersDB.getUsersPost(userId);

        Map<Integer, Map<String, Object>> newMap = new HashMap<>();

        for (int i = 0; i < getUsersPosts.size(); i++) {

            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = getUsersPosts.get(i);
            map.put(userId+" id",map1);

                newMap.put(i, map);
            }
        return newMap;
    }
}
//     if (getUsersPosts.get(i).containsKey(i)){
//             Set<String> key1 = getUsersPosts.get(i).keySet();
//        String value1 = getUsersPosts.get(i).values().toString();
//        map.put(key1.toString(),value1);
//newcom