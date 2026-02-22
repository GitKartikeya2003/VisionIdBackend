package com.example.VisionIdBackend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    String generateToken(String uid);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
