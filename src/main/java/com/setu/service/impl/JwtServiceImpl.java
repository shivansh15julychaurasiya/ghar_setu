package com.setu.service.impl;



import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.setu.config.JwtProperties;
import com.setu.entity.User;
import com.setu.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(User user) {

        Date now = new Date();

        Date expiry =
                new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .subject(user.getMobile())
                .claim("userId", user.getId())
                .claim("firstName", user.getFirstName())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractMobile(String token) {

        return getClaims(token).getSubject();
    }

    @Override
    public boolean validateToken(String token) {

        getClaims(token);

        return true;
    }

    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    @Override
    public boolean isTokenExpired(String token) {

        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }
    
    @Override
    public String extractTokenFromHeader(String header) {

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }

}