package com.springboot.learnspringsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.learnspringsecurity.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
Users findByUsername(String username);
}
