package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.LikeRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;

    public List<Users> getUsers(){return userRepository.findAll();}
    public List<Post>getPosts(){
        return postRepository.findAll();
    }
    public List<Likes>getLikes(){
        return likeRepository.findAll();
    }

    public Set<Likes> getUserLikes(Integer userId){
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get().getLikes();
        } else {
            return null;
        }
    }
    public Set<Commentarie> getPostComment(Integer id){
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()){
            return post.get().getComment();
        }else{
            return null;
        }
    }

}
