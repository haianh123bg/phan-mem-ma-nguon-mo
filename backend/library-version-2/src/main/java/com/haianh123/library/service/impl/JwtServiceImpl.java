package com.haianh123.library.service.impl;

import com.haianh123.library.service.JwtService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.timeoutHours}")
    private int timeoutHours;

    @Value("${jwt.timeoutDays}")
    private int timeoutDays;

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Override
    public String generateToken(UserDetails userDetails) {
        return gennerateToken(new HashMap<>(), userDetails, 1000 * 60 * 60 * timeoutHours);
    }

    @Override
    public String gennerateRefreshToken(UserDetails userDetails) {
        return gennerateToken(new HashMap<>(), userDetails, 1000 * 60 * 60 * 24 * timeoutDays);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String gennerateToken(Map<String, Object> claims, UserDetails userDetails,Integer time) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }


}


















