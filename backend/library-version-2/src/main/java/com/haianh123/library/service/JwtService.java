package com.haianh123.library.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    String gennerateRefreshToken(UserDetails userDetails);

    String extractUsername(String token);

    Boolean isValidToken(String token, UserDetails userDetails);
}
