package com.example.lab7.service;

import com.example.lab7.config.JwtUtils;
import com.example.lab7.entity.User;
import com.example.lab7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;

    public String registerUser(User user) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Registration Task");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        emailService.sendSimpleEmail(
                user.getEmail(),
                "Успешная регистрация",
                "Привет, " + user.getUsername() + "! Ты успешно зарегистрировался в Lab7."
        );
        stopWatch.stop();
        System.out.println("Время выполнения регистрации: " + stopWatch.getTotalTimeMillis() + " мс");

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