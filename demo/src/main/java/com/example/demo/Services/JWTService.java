package com.example.demo.Services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String usernameToken = getUsernameFromToken(token);

        return (usernameToken.equals(userDetails.getUsername()) && !isTokenExpirated(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .claims(extraClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 30)))
                .signWith(getKey())
                .compact();
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpirated(String token) {
        return getExpiration(token).before(new Date());
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("DEMOSPRING");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
