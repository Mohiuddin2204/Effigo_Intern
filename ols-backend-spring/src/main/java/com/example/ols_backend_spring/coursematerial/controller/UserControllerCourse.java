package com.example.ols_backend_spring.coursematerial.controller;




import com.example.ols_backend_spring.coursematerial.entity.User;
import com.example.ols_backend_spring.coursematerial.service.UserService;
import com.example.ols_backend_spring.security.entity.UserInfo;
import com.example.ols_backend_spring.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserControllerCourse {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/admin/api/users")
    public List<User> listAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/auth/user/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping({"/auth/user/api/users/username/{userName}", "/auth/admin/api/users/username/{userName}"})
    public ResponseEntity<User> getUserByUsername(@PathVariable String userName){
        User user = userService.getUserByName(userName);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/auth/user/api/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/user/api/users/{userId}/enroll/{courseId}")
    public ResponseEntity<User> enrollUserInCourse(@PathVariable Long userId, @PathVariable Long courseId){
        User updatedUser = userService.enrollUserInCourse(userId, courseId);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/auth/user/api/users/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId, @RequestParam String userNewEmail){
        userService.updateUserEmail(userId, userNewEmail);
        return ResponseEntity.ok().build();
    }

}