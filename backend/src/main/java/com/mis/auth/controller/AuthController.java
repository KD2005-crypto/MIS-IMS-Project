package com.mis.auth.controller;

import com.mis.auth.dto.LoginRequest;
import com.mis.auth.dto.RegisterRequest;
import com.mis.auth.entity.User;
import com.mis.auth.repository.UserRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserRepository userRepo,
                          BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (userRepo.findByEmail(req.email).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setFull_name(req.fullName);
        user.setEmail(req.email);
        user.setPassword_hash(encoder.encode(req.password));
        user.setRole(req.role);

        userRepo.save(user);
        return "Registered successfully";
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest req) {

        User user = userRepo.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.password, user.getPassword_hash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return Map.of(
                "message", "Login successful",
                "role", user.getRole()
        );
    }

    // FORGOT PASSWORD
    @PostMapping("/forgot-password")
    public String forgotPassword() {
        return "Reset link sent to email";
    }

    // RESET PASSWORD
    @PostMapping("/reset-password")
    public String resetPassword() {
        return "Password reset successful";
    }
}
