package com.example.socialnetwork.repository;

import com.example.socialnetwork.models.Commentarie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Commentarie,Integer> {
}
