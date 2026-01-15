package com.mis.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // ✅ Password encoder (required for AuthController)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Main security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF (needed for frontend → backend POST)
            .csrf(csrf -> csrf.disable())

            // ✅ Authorization rules
            .authorizeHttpRequests(auth -> auth

                // ✅ Render health check
                .requestMatchers("/actuator/health").permitAll()

                // ✅ Auth APIs
                .requestMatchers("/api/auth/**").permitAll()

                // ❌ Everything else secured
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
