package com.mis.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String health() {
        return "MIS-IMS Backend is running";
    }

    @GetMapping("/favicon.ico")
    public void favicon() {
        // prevents 502 from browser favicon request
    }
}
