package com.example.sep_drive_backend.controller;

import com.example.sep_drive_backend.dto.UserProfileResponse;
import com.example.sep_drive_backend.models.Customer;
import com.example.sep_drive_backend.models.Driver;
import com.example.sep_drive_backend.models.users;
import com.example.sep_drive_backend.repository.CustomerRepository;
import com.example.sep_drive_backend.repository.DriverRepository;
import com.example.sep_drive_backend.repository.UserRepository;
import com.example.sep_drive_backend.models.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Get public profile of any user by username (No authentication required)
    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String username) {
        Optional<users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        users user = userOpt.get();
        UserProfileResponse dto = buildUserProfile(user);
        return ResponseEntity.ok(dto);
    }

    // Get all user profiles (No authentication required)
    @GetMapping("/")
    public ResponseEntity<List<UserProfileResponse>> getAllUserProfiles() {
        List<users> allUsers = userRepository.findAll();  // Fetch all users
        List<UserProfileResponse> userProfiles = allUsers.stream()
                .map(this::buildUserProfile)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userProfiles);
    }

    // Get current logged-in user's profile using token (Authentication required)
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getOwnProfile(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = jwtTokenProvider.getUsername(token);
        Optional<users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        users user = userOpt.get();
        UserProfileResponse dto = buildUserProfile(user);
        return ResponseEntity.ok(dto);
    }

    // Helper method to map User -> UserProfileResponse
    private UserProfileResponse buildUserProfile(users user) {
        UserProfileResponse dto = new UserProfileResponse();
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());

        return dto;
    }
}
