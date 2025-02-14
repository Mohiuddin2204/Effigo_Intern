package com.example.ols_backend_spring.security.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.ols_backend_spring.security.entity.AuthRequest;
import com.example.ols_backend_spring.security.entity.UserInfo;
import com.example.ols_backend_spring.security.service.JwtService;
import com.example.ols_backend_spring.security.service.UserInfoService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(){
        return "Welcome to user profile..";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile(){
        return "Welcome to admin profile..";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<Object>authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(new TokenResponse(token));
            }else {
                throw new UsernameNotFoundException("Invalid user request..!!");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed.. " + e.getMessage());
        }
    }

    @Setter
    @Getter
    public static class TokenResponse{
        private String token;

        public TokenResponse(String token){
            this.token = token;
        }

    }
}