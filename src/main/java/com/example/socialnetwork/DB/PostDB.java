package com.example.socialnetwork.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

public class PostDB {

    Connection connection;

    public PostDB() {
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

    public Map<String,Object> getPost(int id){

        Map<String,Object>posts = new HashMap<>();
        try {

            String post = "SELECT * FROM post " +
                    "INNER JOIN users author on post.users_id=author.id " +
                    "WHERE post.id = "+id;

            var stm = connection.createStatement();
            var sttm = stm.executeQuery(post);


            while (sttm.next()) {
                String name = sttm.getString("name");
                String contento = sttm.getString("contento");
                Date date = sttm.getDate("date");
                Time time = sttm.getTime("time");

                posts.put("name", name);
                posts.put("contento", contento);
                posts.put("date", date);
                posts.put("time", time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }
    public List<String> getLikes(int id){

        return new ArrayList<>();
    }

    public List<Map<String,Object>> getComments(int id){

        return new ArrayList<>();
    }
}
