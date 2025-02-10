package com.springboot.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.security.models.User;

@Service
public class UserService {

	
		List<User> list=new ArrayList<>();//fake list

		public UserService() {
		
		}
		public List<User> getAllUsers(){
			return this.list;
		}
		public User getUser(String username) {
			return this.list.stream().filter((user)->
			user.getUsername().equals(username)).findAny().orElse(null);
		}
		public User addUser(User user) {
			this.list.add(user);
			return user;
		}
}
