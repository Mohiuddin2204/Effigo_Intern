package com.webapp.spring_boot_todo.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String name,String password) {
		boolean isValidName=name.equalsIgnoreCase("Sai Veena");
		boolean isValidPassword=password.equalsIgnoreCase("saiveena");
		
		return isValidName && isValidPassword;
	}
}
