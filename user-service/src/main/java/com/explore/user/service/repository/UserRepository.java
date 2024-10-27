package com.explore.user.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.explore.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findByToken(String token);

}
