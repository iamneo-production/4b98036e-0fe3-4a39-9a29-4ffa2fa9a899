package com.faultreporting.authentication_service.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.faultreporting.authentication_service.model.AuthRequest;
import com.faultreporting.authentication_service.model.AuthResponse;
import com.faultreporting.authentication_service.model.TokenRequest;
import com.faultreporting.authentication_service.model.User;
import com.faultreporting.authentication_service.repository.AuthRepository;
import com.faultreporting.authentication_service.service.AuthService;
import com.faultreporting.authentication_service.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthRepository repository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

	@Override
	public ResponseEntity<?> registerUser(User user) {
		try {
            log.info("Registering user {}", user.toString());
            repository.save(user);
            return ResponseEntity.ok("Registration Successful");
        } catch (Exception e) {
            log.error("Registering user {} unsuccessful", user.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to register the user");
        }
	}

	@Override
	public ResponseEntity<?> authenticateUser(AuthRequest request) {
		try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            log.error("Invalid Credentials {} | Authentication Failed", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthResponse.builder().token(jwtToken).build());
	}

	@Override
	public ResponseEntity<?> validateToken(TokenRequest request) {
		return (jwtTokenUtil.validateToken(request.getToken()))
                        ? ResponseEntity.ok("Token is valid") 
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
	}

    
}
