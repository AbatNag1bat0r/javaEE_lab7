package com.example.lab7.dto;

import lombok.Data;
import java.util.List;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private List<String> details;
}