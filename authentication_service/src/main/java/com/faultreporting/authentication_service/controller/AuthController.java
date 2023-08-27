package com.faultreporting.authentication_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faultreporting.authentication_service.model.AuthRequest;
import com.faultreporting.authentication_service.model.TokenRequest;
import com.faultreporting.authentication_service.model.User;
import com.faultreporting.authentication_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.data.rest.basePath}")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        return authService.authenticateUser(request);
    }

    @PostMapping("v1/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequest request) {
        return authService.validateToken(request);
    }
}
