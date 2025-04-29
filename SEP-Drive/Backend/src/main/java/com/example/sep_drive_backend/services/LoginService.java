package com.example.sep_drive_backend.services;

import com.example.sep_drive_backend.dto.LoginRequest;
import com.example.sep_drive_backend.models.Customer;
import com.example.sep_drive_backend.models.Driver;
import com.example.sep_drive_backend.repository.CustomerRepository;
import com.example.sep_drive_backend.repository.DriverRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final DriverRepository driverRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginService(DriverRepository driverRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.driverRepository = driverRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean loginUser(LoginRequest loginRequest) {
        Optional<Customer> customer = customerRepository.findByUsername(loginRequest.getUsername());
        if (customer.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), customer.get().getPassword())) {
            return true;
        }

        Optional<Driver> driver = driverRepository.findByUsername(loginRequest.getUsername());
        if (driver.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), driver.get().getPassword())) {
            return true;
        }

        return false; // user not found or wrong password
    }
}
