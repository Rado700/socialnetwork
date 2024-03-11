package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Post;

public class PostIdComment {

    private Integer id;

    private Post post;

    private String comment;

    public PostIdComment(Integer id, Post post, String comment) {
        this.id = id;
        this.post = post;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
