package com.example.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.EnableAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@EnableAsync
public class Lab7Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab7Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void actionAfterStartup() {
        log.info("=======================================");
        log.info("Лабораторная работа №7 успешно запущена!");
        log.info("Проект готов к работе с MongoDB, JWT, UNIT-тест, Web-тест и Logging .");
        log.info("=======================================");
    }
}