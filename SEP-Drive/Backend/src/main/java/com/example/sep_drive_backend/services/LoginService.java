package com.example.sep_drive_backend.services;

import com.example.sep_drive_backend.dto.LoginRequest;
import com.example.sep_drive_backend.models.Customer;
import com.example.sep_drive_backend.models.Driver;
import com.example.sep_drive_backend.repository.CustomerRepository;
import com.example.sep_drive_backend.repository.DriverRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginService {

    private final DriverRepository driverRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    public LoginService(DriverRepository driverRepository, CustomerRepository customerRepository,
                        PasswordEncoder passwordEncoder, EmailService emailService) {
        this.driverRepository = driverRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public String loginUser(LoginRequest loginRequest) {
        Optional<Customer> customer = customerRepository.findByUsername(loginRequest.getUsername());
        if (customer.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), customer.get().getPassword())) {
            if (!customer.get().isEmailVerified()) {
                sendVerificationCode(customer.get().getEmail(), customer.get().getUsername());
                return "Email verification required. Check your inbox.";
            }
            return "Login successful!";
        }

        Optional<Driver> driver = driverRepository.findByUsername(loginRequest.getUsername());
        if (driver.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), driver.get().getPassword())) {
            if (!driver.get().isEmailVerified()) {
                sendVerificationCode(driver.get().getEmail(), driver.get().getUsername());
                return "Email verification required. Check your inbox.";
            }
            return "Login successful!";
        }

        return "Invalid username or password";
    }

    private void sendVerificationCode(String email, String username) {
        String code = String.format("%06d", new Random().nextInt(999999));
        verificationCodes.put(username, code);
        emailService.sendVerificationCode(email, code);
    }

    public boolean verifyCode(String username, String code) {
        String storedCode = verificationCodes.get(username);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(username);

            Optional<Customer> customer = customerRepository.findByUsername(username);
            if (customer.isPresent()) {
                Customer c = customer.get();
                c.setEmailVerified(true);
                customerRepository.save(c);
                return true;
            }

            Optional<Driver> driver = driverRepository.findByUsername(username);
            if (driver.isPresent()) {
                Driver d = driver.get();
                d.setEmailVerified(true);
                driverRepository.save(d);
                return true;
            }
        }
        return false;
    }
}
