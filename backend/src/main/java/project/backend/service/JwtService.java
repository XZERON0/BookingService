package project.backend.service;
import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@Service
public class JwtService {
    @SuppressWarnings("deprecation")
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long refreshExpirationTime = 604800000; // 1 день

    @SuppressWarnings("deprecation")
    public String generateAccessToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 15*60000)) // 15 минут
            .signWith(secretKey)
            .compact();
    }
    
    @SuppressWarnings("deprecation")
    public String generateRefreshToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationTime)) // 7 дней
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
        } catch (SecurityException | MalformedJwtException e) {
        System.out.println("Invalid JWT format: " + e.getMessage()); // Лог ошибки
        return false;
    } catch (Exception e) {
    System.out.println("JWT validation error: " + e.getMessage()); // Общий лог ошибок
    return false;
}
    }

    @SuppressWarnings("deprecation")
    public long getRemainingTime(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
        Date expiration = claims.getExpiration();
        return expiration.getTime() - System.currentTimeMillis();
    }
    
    @SuppressWarnings("deprecation")
    public Authentication getAuthentication(String token) {
        String email = Jwts.parser()
                           .setSigningKey(secretKey)
                           .build()
                           .parseClaimsJws(token)
                           .getBody()
                           .getSubject();
        // return new UsernamePasswordAuthenticationToken(email, null, getRolesFromToken(token));

        return new UsernamePasswordAuthenticationToken(email, null, List.of()); // Заменить на роли
    }
}
