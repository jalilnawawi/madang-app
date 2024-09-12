package com.jalil_be_app.madang_app.service.jwt;

import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    UserRepository userRepository;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public String generateToken(Authentication authentication) {
        String username;
        UUID userId;
        MyUserDetails user = new MyUserDetails();

        if (authentication.getPrincipal() instanceof MyUserDetails){
            MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();
            username = userPrincipal.getUsername();
            userId = userPrincipal.getId();

            user.setUsername(username);
            user.setId(userId);
            log.info("Generating token for user : {}", userId);
        } else {
            throw new IllegalArgumentException("Unsupported principal type");
        }

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(Authentication authentication) {
        String username;
        UUID userId;
        MyUserDetails user = new MyUserDetails();

        if (authentication.getPrincipal() instanceof MyUserDetails){
            MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();
            username = userPrincipal.getUsername();
            userId = userPrincipal.getId();

            user.setUsername(username);
            user.setId(userId);
            log.info("Generating token for user : {}", userId);
        } else {
            throw new IllegalArgumentException("Unsupported principal type");
        }

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateTokenFromUsername(String username) {
        User user = userRepository.findByUsername(username).get();

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("userId", user.getId())
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public String getUsername(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()).build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    @Override
    public UUID getId(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()).build()
                .parseClaimsJws(jwt)
                .getBody()
                .get("userId", UUID.class);
    }
}
