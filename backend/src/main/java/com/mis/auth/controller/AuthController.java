package com.mis.auth.controller;

import com.mis.auth.dto.LoginRequest;
import com.mis.auth.dto.RegisterRequest;
import com.mis.auth.entity.User;
import com.mis.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "EMAIL_EXISTS";
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setStatus("ACTIVE");

        userRepository.save(user);
        return "REGISTERED";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return userRepository.findByEmail(request.getEmail())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .map(u -> "LOGIN_SUCCESS")
                .orElse("INVALID_CREDENTIALS");
    }
}
