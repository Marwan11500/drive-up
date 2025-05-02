package com.example.sep_drive_backend.controller;

import com.example.sep_drive_backend.dto.LoginRequest;
import com.example.sep_drive_backend.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String loginResponse = loginService.loginUser(loginRequest);

        if (loginResponse.equals("Email verification required. Check your inbox.")) {
            return ResponseEntity.status(401).body(loginResponse);
        }

        if (loginResponse.equals("Login successful!")) {
            return ResponseEntity.ok(loginResponse);
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String username, @RequestParam String code) {
        boolean verified = loginService.verifyCode(username, code);

        if (verified) {
            return ResponseEntity.ok("Email verification successful! You can now log in.");
        } else {
            return ResponseEntity.status(400).body("Invalid verification code or user.");
        }
    }
}
