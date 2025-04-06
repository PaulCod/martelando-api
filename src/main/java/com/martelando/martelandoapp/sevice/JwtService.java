package com.martelando.martelandoapp.sevice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretString = "ahsdMNXB3FZ2gsjULydWXvYFofSTUrezGbVaNIjVQNCWIjaph2KqCXUfG7V8jZTBF2tRJiPPYzEMrrIKpZ/aug==";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretString));


    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token, Long id) {
        Long userId = extractUserId(token);
        return userId.equals(id) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(Long userId) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("userId", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    public Long extractUserIdFromToken(String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer")
                ? authorizationHeader.substring(7).trim()
                : authorizationHeader.trim();
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token)
                .getPayload();
        return claims.get("userId", Long.class);
    }

}
