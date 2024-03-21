package com.example.socialnetwork.services;

import com.example.socialnetwork.dto.PostDTO;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.PostRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getPost(Integer id){
        return postRepository.findById(id).orElseThrow();
    }

    public Post deletePosts(Integer id) throws Exception {
        Post post = getPost(id);
        if (post == null){
            throw new Exception("такого поста нету");
        }
        postRepository.delete(post);
        return post;

    }
    public Post addPost(String contento, Date date, Time time,Users users){

        Post post = new Post(contento,date,time,users);
        postRepository.save(post);
        return post;
    }

//    public Set<Post> getUserPost(Integer id){
//        Optional<Post> post = postRepository.findById(id);
//        return post.map(Post::getContento).orElse(null);
//    }
}
