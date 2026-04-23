package com.example.lab7.service;

import com.example.lab7.config.JwtUtils;
import com.example.lab7.entity.User;
import com.example.lab7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;

    public String registerUser(User user) {
        log.info("Начало регистрации пользователя: {}", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        emailService.sendSimpleEmail(
                user.getEmail(),
                "Успешная регистрация",
                "Привет, " + user.getUsername() + "! Ты успешно зарегистрировался в Lab7."
        );

        log.info("Пользователь {} успешно сохранен и письмо отправлено", user.getUsername());
        return "Пользователь " + user.getUsername() + " успешно зарегистрирован!";
    }

    public String loginUser(String username, String password) {
        log.info("Попытка входа пользователя: {}", username);
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            log.info("Пользователь {} успешно авторизован", username);
            return jwtUtils.generateToken(user.getUsername());
        }

        log.warn("Неудачная попытка входа для пользователя: {}", username);
        throw new RuntimeException("Ошибка: Неверный логин или пароль!");
    }
}