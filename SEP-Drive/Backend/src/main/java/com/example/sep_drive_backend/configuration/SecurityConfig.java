package com.example.sep_drive_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource())) // Neue CORS Konfiguration
                .csrf(csrf -> csrf.disable())  // CSRF deaktivieren
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // Preflight-Request zulassen
                        .requestMatchers("/", "/home", "/h2-console/**", "/api/auth/**", "/api/users/**").permitAll()  // Öffentliche Endpunkte
                        .anyRequest().authenticated()  // Alle anderen Endpunkte erfordern Authentifizierung
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))  // für H2-Konsole
                .formLogin(form -> form.disable()) // Form Login deaktivieren
                .logout(logout -> logout.logoutUrl("/api/auth/logout"))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // JWT-Filter hinzufügen

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));  // Erlaube Anfragen von Angular-Frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Erlaube bestimmte HTTP-Methoden
        config.setAllowedHeaders(List.of("*"));  // Erlaube alle Header
        config.setAllowCredentials(true);  // Erlaube Cookies (falls nötig)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);  // CORS für alle API-Endpunkte aktivieren

        return source;
    }
}
