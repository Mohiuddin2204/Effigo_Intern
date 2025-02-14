package com.example.ols_backend_spring.coursematerial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ols_backend_spring.coursematerial.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}

