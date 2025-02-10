package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.security.models.User;
import com.springboot.security.repo.UserRepository;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcpe;
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername("user");
		user.setEmail("user@gmail.com");
		user.setPassword(this.bcpe.encode("user123"));
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);
		
		User user1=new User();
		user1.setUsername("admin");
		user1.setEmail("admin@gmail.com");
		user1.setPassword(this.bcpe.encode("admin123"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
	}

}
