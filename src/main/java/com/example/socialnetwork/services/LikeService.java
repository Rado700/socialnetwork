package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Likes;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    public List<Likes> getAllLikes(){
        return likeRepository.findAll();
    }

    public Likes addLike(Users user, Post post){
        Likes like = new Likes(user,post);
        likeRepository.save(like);
        return like;
    }

    public Likes getLike(Integer likeId){
        return likeRepository.findById(likeId).orElse(null);
    }

    public Set<Likes> getLikes(Integer id){
        Optional<Likes>like = likeRepository.findById(id);
        return like.map(Likes::getUsers).orElse(null).getLikes();
    }

    public Likes deleteLike(Users user,Post post){
        Likes like = new Likes(user,post);
        likeRepository.delete(like);
        return like;
    }

    public Likes deleteLike(Integer user_id) throws Exception {
        Likes like = getLike(user_id);
        if (like == null){
            throw new Exception("Нету лайка");
        }else {
            likeRepository.delete(like);
        }
        return like;
    }


}
