package com.nrw.non_revenue_water.service.impl;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.service.IJWTService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements IJWTService {
    private final String secret = "97320f9daa624eb18136e8bb455549f699ba272e18c44557b430d88789adfe8f";

    @Override
    public String generateToken(String accountNumber) {
        return Jwts.builder()
                .signWith(getKey())
                .subject(accountNumber)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    private SecretKey getKey() {
        byte bytes[] = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

}
