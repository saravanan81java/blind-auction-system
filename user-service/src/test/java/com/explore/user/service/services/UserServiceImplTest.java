package com.explore.user.service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.explore.user.service.entity.User;
import com.explore.user.service.repository.UserRepository;
import com.explore.user.service.services.Impl.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testValidateToken_Valid() {
        String token = "validToken";
        User mockUser = new User(); // Assuming a valid user object
        when(userRepository.findByToken(token)).thenReturn(Optional.of(mockUser));

        User user = userService.validateToken(token);
        assertNotNull(user);
    }

    @Test
    public void testValidateToken_Invalid() {
        String token = "invalidToken";
        when(userRepository.findByToken(token)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.validateToken(token);
        });
        assertEquals("Invalid token", exception.getMessage());
    }

}
