package com.springboot.learnspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.learnspringsecurity.model.Users;
import com.springboot.learnspringsecurity.repo.UserRepo;

@Service
public class UserService {
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	private UserRepo repo;
	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	
	}
	public String verify(Users user) {
		// TODO Auto-generated method stub
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		
	}
}
