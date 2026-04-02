package com.example.lab7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/test")
    public String vivod(){
        System.out.println("Hey!");
        return "Vse! ili";
    }
}
