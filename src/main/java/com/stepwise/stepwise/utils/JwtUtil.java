package com.stepwise.stepwise.utils;

import com.stepwise.stepwise.entity.ConfUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "e98tOoXt4rUOYanngpxmbTRXcA0MuzW3z9DPs56q6yI="; // Replace with your actual secret key
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 days

    public static String generateToken(ConfUserEntity username) {
        return Jwts.builder()
                .setSubject(username.getUserUsername())
                .claim("username", username.getUserUsername())
                .claim("userType" , username.getUserType())
                .claim("userId" , username.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Generate Refresh Token
    public static String generateRefreshToken(ConfUserEntity username) {
        return Jwts.builder()
                .setSubject(username.getUserUsername())
                .claim("username", username.getUserUsername())
                .claim("userType" , username.getUserType())
                .claim("userId" , username.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static Object extractClaim(String token, String claimKey) {
        return extractClaims(token).get(claimKey);
    }

    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

