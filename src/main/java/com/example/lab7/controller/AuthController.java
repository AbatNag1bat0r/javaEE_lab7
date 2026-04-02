package com.example.lab7.controller;

import com.example.lab7.entity.User;
import com.example.lab7.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        return authService.loginUser(loginData.getUsername(), loginData.getPassword());
    }
}