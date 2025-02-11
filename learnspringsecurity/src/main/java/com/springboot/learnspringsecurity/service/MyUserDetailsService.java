package com.springboot.learnspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.learnspringsecurity.model.UserPrincipal;
import com.springboot.learnspringsecurity.model.Users;
import com.springboot.learnspringsecurity.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=repo.findByUsername(username);
		if(user==null) {
			System.out.println("not found");
			throw new UsernameNotFoundException("not found");
			
		}
		return new UserPrincipal(user) ;
	}

}
