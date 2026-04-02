package com.example.lab7.service;

import com.example.lab7.config.JwtUtils;
import com.example.lab7.entity.User;
import com.example.lab7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Пользователь " + user.getUsername() + " успешно зарегистрирован!";
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtils.generateToken(user.getUsername());
        }
        throw new RuntimeException("Ошибка: Неверный логин или пароль!");
    }
}