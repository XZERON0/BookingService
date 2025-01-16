package project.backend.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.backend.service.JwtService;

@RestController
@RequestMapping("/system")
public class SystemController {


    private JwtService jwtService;
    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (jwtService.validateToken(refreshToken)) {
            String newAccessToken = jwtService.generateAccessToken(jwtService.getAuthentication(refreshToken).getName());
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }
    @GetMapping("/token")
    public String checkToken()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        System.out.println("geoo");
        if (authentication.getPrincipal() != "anonymousUser" && authentication.isAuthenticated())
        {
            return "Token is valid";
        }
        return "Token is invalid";
    }
}
