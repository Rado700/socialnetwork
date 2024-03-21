package com.example.socialnetwork.repository;

import com.example.socialnetwork.models.Authorisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorisation,Integer>{
    Authorisation findByLoginAndPassword(String login, String password);
}
