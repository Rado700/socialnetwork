package com.example.socialnetwork.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDB {

    Connection connection;
    public UsersDB() {
        try {

            String jdbcUrl = DatabaseConfig.getDbUrl();
            String user = DatabaseConfig.getDbUsername();
            String password = DatabaseConfig.getDbPassword();

            connection = DriverManager.getConnection(jdbcUrl, user, password);

        } catch (
                SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<List<String>> Users(){

        List<List<String>>user1 = new ArrayList<>();

        try {


           String user = "SELECT name,surname,url,premium FROM users";

           var stms = connection.createStatement();
           var sttm = stms.executeQuery(user);

           while (sttm.next()){
              String name = sttm.getString("name");
              String surname = sttm.getString("surname");
              String url = sttm.getString("url");
              Boolean premium = sttm.getBoolean("premium");

              List<String> newList = new ArrayList<>();
              newList.add(name);
              newList.add(surname);
              newList.add(url);
              newList.add(String.valueOf(premium));

              user1.add(newList);
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return user1;

    }

    public List<Map<String, Object>> getUsersPost (Integer userId){

        List<Map<String,Object>> newObject = new ArrayList<>();

        try {

            String post = "SELECT * FROM users " +
                    "INNER JOIN post on users.id=post.users_id " +
                    "WHERE users.id=" + userId;

            var sttm = connection.createStatement();
            var stt = sttm.executeQuery(post);

            while (stt.next()){
                String name = stt.getString("name");
                int user = stt.getInt("users_id");
                String content = stt.getString("contento");
                Time time = stt.getTime("time");
                Date data = stt.getDate("date");

                Map<String,Object>newMap = new HashMap<>();
                newMap.put("name",name);
                newMap.put("user",user);
                newMap.put("content",content);
                newMap.put("time",time);
                newMap.put("data",data);

                newObject.add(newMap);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newObject;
    }
}
