package com.faultreporting.authentication_service.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 900000); //Token validity si 15 minutes

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            log.info("Parsing the token | Validating");
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.info("Unable to parse the token | Invalid Request");
            return false;
        }
    }


}
