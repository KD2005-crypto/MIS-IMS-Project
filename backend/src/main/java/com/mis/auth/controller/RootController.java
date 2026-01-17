package com.mis.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "MIS-IMS Backend is running on Render";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
