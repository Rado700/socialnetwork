package com.example.socialnetwork.services;

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

    public Map<String, Object> getPost(int id) {

        Map<String, Object> posts = new HashMap<>();
        try {

            String post = "SELECT * FROM post " +
                    "INNER JOIN users author on post.users_id=author.id " +
                    "WHERE post.id = " + id;

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

    public Map<String, Object> getLike(int user_id, int post_id) {

        UsersDB usersDB = new UsersDB();
        Map<String, Object> newName = new HashMap<>();

        newName.put("user", usersDB.getUser(user_id));
        newName.put("post", getPost(post_id));

        return newName;
    }

    public List<Map<String, Object>> getComments(int id) {

        List<Map<String, Object>> comments = new ArrayList<>();

        try {

            String comment = "SELECT * FROM commentarie " +
                    "INNER JOIN users on commentarie.user_id=users.id " +
                    "INNER JOIN post on commentarie.post_id=post.id " +
                    "WHERE post_id = " + id;

            var stmn = connection.createStatement();
            var sttm = stmn.executeQuery(comment);

            while (sttm.next()) {

                Map<String, Object> map = new HashMap<>();
                int user_id = sttm.getInt("user_id");
                String name = sttm.getString("name");
                String text = sttm.getString("text");


                map.put("user_id", user_id);
                map.put("name", name);
                map.put("text", text);

                comments.add(map);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }

    public void addPosts(String contento, Date date, Time time, int users_id) {

        try {
            String add = "INSERT INTO post (contento,date,time,users_id) " +
                    "VALUES (?,?,?,?)";

            var stm = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, contento);
            stm.setDate(2, date);
            stm.setTime(3, time);
            stm.setInt(4, users_id);

            int refresh = stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLikeExist(int user_id, int post_id) {
        try {

            String selectLike = "SELECT * FROM likes " +
                    "WHERE post_id=" + post_id + " AND user_id=" + user_id;


            var stmn = connection.createStatement();
            var sttm = stmn.executeQuery(selectLike);

            while (sttm.next()) {

                return true;

            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLike(int user_id, int post_id) {
        try {
            String newAdd = "INSERT INTO likes(user_id,post_id)" +
                    "VALUES (?,?)";

            var stmn = connection.prepareStatement(newAdd, Statement.RETURN_GENERATED_KEYS);

            stmn.setInt(1, user_id);
            stmn.setInt(2, post_id);

            int refresh = stmn.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addComment(int user_id, int post_id, String text) {

        try {
            String addText = "INSERT INTO commentarie (user_id,post_id,text) " +
                    "VALUES (?,?,?)";

            var stms = connection.prepareStatement(addText, Statement.RETURN_GENERATED_KEYS);


            stms.setInt(1, user_id);
            stms.setInt(2, post_id);
            stms.setString(3, text);


            int refresh = stms.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLike(int user_id, int post_id) {
        try {
            String name = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";
            var sttm = connection.prepareStatement(name);

            sttm.setInt(1, user_id);
            sttm.setInt(2, post_id);

            int insertedRow = sttm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
