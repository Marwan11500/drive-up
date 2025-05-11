package com.example.sep_drive_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/h2-console/**", "/api/auth/register", "/api/auth/login", "/api/ride-requests/**").permitAll() // Adjusted /api/ride-requests/** for wildcard matching
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/auth/register", "/api/auth/login", "/api/ride-requests/**") // Ensures CSRF is ignored for all ride-requests endpoints
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Allows h2-console to be used if needed
                )
                .formLogin(Customizer.withDefaults()) // Default form login handling
                .logout(Customizer.withDefaults()); // Default logout handling

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encryption with BCrypt
    }
}
