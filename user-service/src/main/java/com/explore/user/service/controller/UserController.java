package com.explore.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.explore.user.service.entity.User;
import com.explore.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/ping")
	public String getPing() {
		return "Hello User Service!!!";
	}
	
	@PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("User-Token") String token) {
        String reponse = "Valid User";
		User user = userService.validateToken(token);
        if(user == null) {
        	reponse = "Invalid User";
        } 
        return ResponseEntity.ok(reponse);
    }

}
