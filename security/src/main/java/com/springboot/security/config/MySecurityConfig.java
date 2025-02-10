package com.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.springboot.security.services.CustomUserDetailService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class MySecurityConfig {
	@Autowired
	private CustomUserDetailService cuds;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf->csrf.disable())// CSRF should be disabled outside of authorizeHttpRequests()
	        .authorizeHttpRequests(auth -> auth
	            // Public access for all users
	            .requestMatchers("/home", "/login", "/register","/signin").permitAll()

	            // Admin can access "/users", but NOT "/public/*"
	            .requestMatchers("/users/**").hasRole("ADMIN")

	            // User can access "/public/*", but NOT "/users"
	            .requestMatchers("/public/**").hasRole("NORMAL")

	            // Any other request requires authentication
	            .anyRequest().authenticated()
	            
	            
	        )
	        .formLogin()
	        .loginPage("/signin")
	        .loginProcessingUrl("/dologin")
	        .defaultSuccessUrl("/users/");

	    return http.build();
	}


    @Bean
    public UserDetailsService userDetailsService() {
    return cuds;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
