package com.example.socialnetwork.DB;

import java.sql.*;
import java.util.*;
import java.sql.Date;

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

        List<String> newName = new ArrayList<>();
        try {
            String like = "SELECT name FROM likes " +
                    "INNER JOIN users on likes.user_id=users.id " +
                    "INNER JOIN post on likes.post_id=post.id " +
                    "WHERE post.id =" + id;

            var stmn = connection.createStatement();
            var sttm = stmn.executeQuery(like);

            while (sttm.next()){

                String name = sttm.getString("name");
                newName.add(name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newName;
    }

    public List<Map<String,Object>> getComments(int id){

        List<Map<String,Object>>commentAdd=new ArrayList<>();

        try {

            String comment = "SELECT * FROM commentarie " +
                    "INNER JOIN users on commentarie.user_id=users.id " +
                    "INNER JOIN post on commentarie.post_id=post.id " +
                    "WHERE post_id = "+id;

            var stmn = connection.createStatement();
            var sttm = stmn.executeQuery(comment);

            while (sttm.next()){

                Map<String , Object> map = new HashMap<>();
                int user_id = sttm.getInt("user_id");
                String name = sttm.getString("name");
                String text = sttm.getString("text");


                map.put("user_id", user_id);
                map.put("name", name);
                map.put("text",text);

                commentAdd.add(map);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commentAdd;
    }
    public void addPosts(String contento, Date date, Time time, int users_id){

        try {
            String add = "INSERT INTO post (contento,date,time,users_id) " +
                    "VALUES (?,?,?,?)";

            var stm = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1,contento);
            stm.setDate(2,date);
            stm.setTime(3,time);
            stm.setInt(4,users_id);

            int refresh = stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
