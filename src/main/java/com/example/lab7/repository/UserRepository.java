package com.example.lab7.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.lab7.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}