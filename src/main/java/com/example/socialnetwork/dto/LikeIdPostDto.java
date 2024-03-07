package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Post;

public class LikeIdPostDto {

    private int id;
    private Post post;

    public LikeIdPostDto(int id, Post post_id) {
        this.id = id;
        this.post = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
