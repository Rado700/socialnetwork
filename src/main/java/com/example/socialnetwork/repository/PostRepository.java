package com.example.socialnetwork.repository;

import com.example.socialnetwork.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
