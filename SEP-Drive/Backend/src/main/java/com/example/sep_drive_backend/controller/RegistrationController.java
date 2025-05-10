package com.example.sep_drive_backend.controller;

import com.example.sep_drive_backend.dto.RegistrationRequest;
import com.example.sep_drive_backend.repository.CustomerRepository;
import com.example.sep_drive_backend.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        registrationService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");

    }
}
