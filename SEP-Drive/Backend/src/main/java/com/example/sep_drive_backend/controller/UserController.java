package com.example.sep_drive_backend.controller;

import com.example.sep_drive_backend.dto.CustomerProfileResponse;
import com.example.sep_drive_backend.dto.DriverProfileResponse;
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
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        Optional<users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        users user = userOpt.get();

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            CustomerProfileResponse dto = new CustomerProfileResponse();
            dto.setUsername(customer.getUsername());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setEmail(customer.getEmail());
            dto.setBirthDate(customer.getBirthDate());
            dto.setRole(customer.getRole());
            dto.setRating(customer.getRating());  // Customer-specific field
            dto.setTotalRides(customer.getTotalRides());  // Customer-specific field
            dto.setProfilePicture(customer.getProfilePicture());
            return ResponseEntity.ok(dto);
        } else if (user instanceof Driver) {
            Driver driver = (Driver) user;
            DriverProfileResponse dto = new DriverProfileResponse();
            dto.setUsername(driver.getUsername());
            dto.setFirstName(driver.getFirstName());
            dto.setLastName(driver.getLastName());
            dto.setEmail(driver.getEmail());
            dto.setBirthDate(driver.getBirthDate());
            dto.setRole(driver.getRole());
            dto.setRating(driver.getRating());  // Driver-specific field
            dto.setVehicleClass(driver.getVehicleClass());  // Driver-specific field
            dto.setProfilePicture(driver.getProfilePicture());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Get all user profiles (No authentication required)
    @GetMapping("/")
    public ResponseEntity<List<?>> getAllUserProfiles() {
        List<users> allUsers = userRepository.findAll();  // Fetch all users
        List<Object> userProfiles = allUsers.stream()
                .map(user -> {
                    if (user instanceof Customer) {
                        Customer customer = (Customer) user;
                        CustomerProfileResponse dto = new CustomerProfileResponse();
                        dto.setUsername(customer.getUsername());
                        dto.setFirstName(customer.getFirstName());
                        dto.setLastName(customer.getLastName());
                        dto.setEmail(customer.getEmail());
                        dto.setBirthDate(customer.getBirthDate());
                        dto.setRole(customer.getRole());
                        dto.setRating(customer.getRating());
                        dto.setTotalRides(customer.getTotalRides());
                        dto.setProfilePicture(customer.getProfilePicture());
                        return dto;
                    } else if (user instanceof Driver) {
                        Driver driver = (Driver) user;
                        DriverProfileResponse dto = new DriverProfileResponse();
                        dto.setUsername(driver.getUsername());
                        dto.setFirstName(driver.getFirstName());
                        dto.setLastName(driver.getLastName());
                        dto.setEmail(driver.getEmail());
                        dto.setBirthDate(driver.getBirthDate());
                        dto.setRole(driver.getRole());
                        dto.setRating(driver.getRating());
                        dto.setVehicleClass(driver.getVehicleClass());
                        dto.setProfilePicture(driver.getProfilePicture());
                        return dto;
                    }
                    return null;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(userProfiles);
    }

    // Get current logged-in user's profile using token (Authentication required)
    @GetMapping("/me")
    public ResponseEntity<?> getOwnProfile(HttpServletRequest request) {
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

        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            CustomerProfileResponse dto = new CustomerProfileResponse();
            dto.setUsername(customer.getUsername());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setEmail(customer.getEmail());
            dto.setBirthDate(customer.getBirthDate());
            dto.setRole(customer.getRole());
            dto.setRating(customer.getRating());
            dto.setTotalRides(customer.getTotalRides());
            dto.setProfilePicture(customer.getProfilePicture());
            return ResponseEntity.ok(dto);
        } else if (user instanceof Driver) {
            Driver driver = (Driver) user;
            DriverProfileResponse dto = new DriverProfileResponse();
            dto.setUsername(driver.getUsername());
            dto.setFirstName(driver.getFirstName());
            dto.setLastName(driver.getLastName());
            dto.setEmail(driver.getEmail());
            dto.setBirthDate(driver.getBirthDate());
            dto.setRole(driver.getRole());
            dto.setRating(driver.getRating());
            dto.setVehicleClass(driver.getVehicleClass());
            dto.setProfilePicture(driver.getProfilePicture());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
