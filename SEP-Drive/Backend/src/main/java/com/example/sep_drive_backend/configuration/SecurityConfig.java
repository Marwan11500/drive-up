package com.example.sep_drive_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .requestMatchers("/", "/home", "/h2-console/**", "/api/auth/register", "/api/auth/login", "/api/auth/verify").permitAll()  // Allow verify without authentication
                        .anyRequest().authenticated()  // Require authentication for any other request
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/auth/register", "/api/auth/login", "/api/auth/verify")  // Add /api/auth/verify to ignore CSRF
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .sameOrigin()  // Allow H2 console frame options
                        )
                )
                .formLogin(form -> form
                        .disable()  // Disable default form login page as we're handling login via API
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")  // Customize logout URL if needed
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Password encoder for hashing passwords
    }
}
