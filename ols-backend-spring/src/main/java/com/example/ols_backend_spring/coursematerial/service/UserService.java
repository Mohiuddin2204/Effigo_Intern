package com.example.ols_backend_spring.coursematerial.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.ols_backend_spring.coursematerial.entity.Course;
import com.example.ols_backend_spring.coursematerial.entity.User;
import com.example.ols_backend_spring.coursematerial.repository.CourseRepository;
import com.example.ols_backend_spring.coursematerial.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // get all users list
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // get user by its user id
    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found by Id"));
    }

    public User getUserByName(String userName){
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found by username"));
    }

    // delete a user by id
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    // assign course to user
    public User enrollUserInCourse(Long userId, Long courseId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(userOptional.isPresent() && courseOptional.isPresent() ){
            User user = userOptional.get();
            Course course = courseOptional.get();
            user.getCourses().add(course);
            return userRepository.save(user);
        }else{
            throw new RuntimeException("User or Course not found check the id");
        }
    }

    // update user Email
    public User updateUserEmail(Long userId, String userNewEmail){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        user.setUserEmail(userNewEmail);
        return userRepository.save(user);
    }

}