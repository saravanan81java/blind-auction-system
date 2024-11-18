package com.explore.user.service.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.explore.user.service.entity.User;
import com.explore.user.service.repository.UserRepository;
import com.explore.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
    private UserRepository userRepository;
    
    @Autowired // Its optional @Autowired annotation default Spring will automatically detect and Constructor injection
    public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    public User validateToken(String token) {
        return userRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));
    }

}
