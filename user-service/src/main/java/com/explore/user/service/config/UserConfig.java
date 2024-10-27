package com.explore.user.service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.explore.user.service.entity.User;
import com.explore.user.service.repository.UserRepository;

@Configuration
public class UserConfig {
	/*
	@Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("seller-token-123", "seller"));
            userRepository.save(new User("buyer-token-456", "buyer"));
            userRepository.save(new User("buyer-token-789", "buyer"));
        };
    }
	 */
}
