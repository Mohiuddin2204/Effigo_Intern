package com.springboot.dto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.dto.UserDTO;
import com.springboot.dto.entity.User;
import com.springboot.dto.mapper.UserMapper;
import com.springboot.dto.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(User user) {
        user = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(user);
    }
}