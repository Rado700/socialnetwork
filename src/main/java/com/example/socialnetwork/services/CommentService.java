package com.example.socialnetwork.services;

import com.example.socialnetwork.models.Commentarie;
import com.example.socialnetwork.models.Post;
import com.example.socialnetwork.models.Users;
import com.example.socialnetwork.repository.CommentRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public List<Commentarie>getCommentaries(){
        return commentRepository.findAll();
    }
    public Commentarie getCommentarie(Integer id){
        return commentRepository.findById(id).orElse(null);
    }

    public Commentarie addcomment(Users user, Post post,String text){
        Commentarie commentarie = new Commentarie(user,post, text);
        commentRepository.save(commentarie);
        return commentarie;
    }

    public Commentarie deleteComment(Integer id) throws Exception {
        Commentarie commentarie = getCommentarie(id);
        if (commentarie == null){
            throw new Exception("Нету комментария");
        }
        else {
            commentRepository.delete(commentarie);
        }
        return commentarie;
    }

//    public Set<Commentarie> getUserComment(Integer id){
//        Optional<Commentarie> user = commentRepository.findById(id);
//        return user.map(Commentarie::getUsers).orElse(null).getCommentaries();
//    }
//    public Set<Commentarie> getUserComment(Integer id){
//        Optional<Users> user = userRepository.findById(id);
//        return user.map(Users::getCommentaries).orElse(null);
//    }
}
