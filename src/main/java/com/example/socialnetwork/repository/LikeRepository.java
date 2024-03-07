package com.example.socialnetwork.repository;

import com.example.socialnetwork.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Integer> {
}
