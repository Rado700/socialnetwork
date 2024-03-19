package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getPost(Integer post_id){
        return postRepository.findById(post_id).orElseThrow();
    }
    public List<Post>getPosts(Integer id){
        return Collections.singletonList(postRepository.findById(id).orElseThrow());
    }
    public Post deletePosts(Integer id) throws Exception {
        Post post = getPost(id);
        if (post == null){
            throw new Exception("такого поста нету");
        }
        postRepository.delete(post);
        return post;

    }

//    public Set<Post> getUserPost(Integer id){
//        Optional<Post> post = postRepository.findById(id);
//        return post.map(Post::getContento).orElse(null);
//    }
}
