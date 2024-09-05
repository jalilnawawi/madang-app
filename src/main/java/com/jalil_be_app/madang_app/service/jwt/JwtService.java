package com.jalil_be_app.madang_app.service.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public interface JwtService {
    public String extractUsername(String token);
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public String generateToken(Authentication authentication);
    public String generateTokenFromUsername(String username);
    public String getUsername(String jwt);
    public UUID getId(String jwt);
}
