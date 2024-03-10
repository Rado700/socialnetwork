package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Post;

public class PostIdComment {

    private Integer id;

    private Post post;

    public PostIdComment(Integer id, Post post) {
        this.id = id;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
