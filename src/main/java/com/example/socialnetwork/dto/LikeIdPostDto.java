package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;

public class LikeIdPostDto {

    private Users users;
    private Post post;

    public LikeIdPostDto(Post post, Users users) {
    }


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
