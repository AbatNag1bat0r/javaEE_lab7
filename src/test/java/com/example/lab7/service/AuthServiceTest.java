package com.example.lab7.service;

import com.example.lab7.entity.User;
import com.example.lab7.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthService authService;

    @Test
    void testRegisterUser_Success() {
        User user = new User();
        user.setUsername("test_user");
        user.setPassword("raw_password");
        user.setEmail("test@test.com");

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        String result = authService.registerUser(user);

        assertTrue(result.contains("успешно зарегистрирован"));
        verify(userRepository, times(1)).save(any(User.class));
        verify(emailService, times(1)).sendSimpleEmail(any(), any(), any());
    }
}