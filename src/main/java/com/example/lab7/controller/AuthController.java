package com.example.lab7.controller;

import com.example.lab7.entity.User;
import com.example.lab7.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        log.info("Получен запрос на регистрацию: {}", user.getUsername());
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        log.info("Получен запрос на вход: {}", loginData.getUsername());
        return authService.loginUser(loginData.getUsername(), loginData.getPassword());
    }
}