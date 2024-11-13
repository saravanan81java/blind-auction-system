package com.explore.user.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.explore.user.service.entity.User;
import com.explore.user.service.services.UserService;

@SpringBootTest
public class UserControllerTest {

	 	@Mock
	    private UserService userService;

	    @InjectMocks
	    private UserController userController;

	    @Test
	    public void testGetPing() {
	        // Test the /ping end-point
	        String result = userController.getPing();
	        assertEquals("Hello User Service!!!", result);
	    }

	    @Test
	    public void testValidateToken_ValidUser() {
	        // Test the /validate end-point for a valid token
	        String token = "validToken";
	        User mockUser = new User(); // Assuming a valid user object
	        when(userService.validateToken(token)).thenReturn(mockUser);

	        ResponseEntity<String> response = userController.validateToken(token);
	        assertEquals("Valid User", response.getBody());
	    }

	    @Test
	    public void testValidateToken_InvalidUser() {
	        // Test the /validate end-point for an invalid token
	        String token = "invalidToken";
	        when(userService.validateToken(token)).thenReturn(null);

	        ResponseEntity<String> response = userController.validateToken(token);
	        assertEquals("Invalid User", response.getBody());
	    }	
}
