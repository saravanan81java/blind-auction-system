package com.explore.user.service.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.explore.user.service.entity.User;
import com.explore.user.service.repository.UserRepository;
import com.explore.user.service.services.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;

    public User validateToken(String token) {
        return userRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));
    }

}
