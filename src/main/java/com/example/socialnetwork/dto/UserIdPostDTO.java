package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;

import java.util.Set;

public class UserIdPostDTO {

    Set<Post> post;
    Users user;

    public UserIdPostDTO(){

    }

    public UserIdPostDTO(Set<Post> post, Users users) {
        this.post = post;
        this.user = users;
    }

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
