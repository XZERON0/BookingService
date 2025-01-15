package project.backend.service;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import project.backend.models.User;

@Service
public class JwtService {
    @SuppressWarnings("deprecation")
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 86400000; // 1 день

    @SuppressWarnings("deprecation")
    public String generateToken(Optional<User> user) {
        return Jwts.builder()
        .setSubject(user.map(User:: getEmail).orElse(""))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(secretKey)
        .compact();
    }
    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SecurityException | IllegalArgumentException e) {
            return false;
        }
    }
    
    @SuppressWarnings("deprecation")
    public Authentication getAuthentication(String token) {
        String email = Jwts.parser()
                           .setSigningKey(secretKey)
                           .build()
                           .parseClaimsJws(token)
                           .getBody()
                           .getSubject();
        return new UsernamePasswordAuthenticationToken(email, null, List.of()); // Заменить на роли
    }
}
