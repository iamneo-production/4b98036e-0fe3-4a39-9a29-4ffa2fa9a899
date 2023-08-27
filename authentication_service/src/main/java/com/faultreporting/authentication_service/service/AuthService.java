package com.faultreporting.authentication_service.service;

import org.springframework.http.ResponseEntity;

import com.faultreporting.authentication_service.model.AuthRequest;
import com.faultreporting.authentication_service.model.TokenRequest;
import com.faultreporting.authentication_service.model.User;

public interface AuthService {
    ResponseEntity<?> registerUser(User user);
    ResponseEntity<?> authenticateUser(AuthRequest request);
    ResponseEntity<?> validateToken(TokenRequest request);
}
