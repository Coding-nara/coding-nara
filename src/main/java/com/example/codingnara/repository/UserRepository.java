package com.example.codingnara.repository;

import com.example.codingnara.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByname(String name);
}
