package com.springboot.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.models.User;

public interface UserRepository extends JpaRepository<User,String> {
	Optional<User> findByUsername(String username);

}
