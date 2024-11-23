package com.dteam.momentree.application.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;

public class JWTUtil {

    public static String generateToken(Long userId, String secretKey, Long expirationSeconds) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Claims validateToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long getUserIdFromToken(String token, String secretKey) {
        return Long.parseLong(validateToken(token, secretKey).getSubject());
    }

    public static Date getExpireDateFromToken(String token, String secretKey) {
        return validateToken(token, secretKey).getExpiration();
    }
}