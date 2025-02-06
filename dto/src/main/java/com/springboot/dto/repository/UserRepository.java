package com.springboot.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.dto.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}