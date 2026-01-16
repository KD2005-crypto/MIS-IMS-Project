package com.mis.auth.controller;

import com.mis.auth.dto.LoginRequest;
import com.mis.auth.dto.RegisterRequest;
import com.mis.auth.entity.User;
import com.mis.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email already exists"));
        }

        User user = new User();
        user.setName(request.getName());       // ✅ NOW EXISTS
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return ResponseEntity.ok(
                Map.of("message", "Registration successful")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null ||
            !passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            return ResponseEntity.status(401)
                    .body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.ok(
                Map.of(
                        "email", user.getEmail(),
                        "role", user.getRole()
                )
        );
    }
}
