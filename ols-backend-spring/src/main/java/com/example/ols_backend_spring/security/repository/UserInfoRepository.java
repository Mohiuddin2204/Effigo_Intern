package com.example.ols_backend_spring.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ols_backend_spring.security.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
}