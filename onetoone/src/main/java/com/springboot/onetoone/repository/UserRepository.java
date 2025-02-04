package com.springboot.onetoone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.onetoone.model.Address;
import com.springboot.onetoone.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByAddress(Address address);

}