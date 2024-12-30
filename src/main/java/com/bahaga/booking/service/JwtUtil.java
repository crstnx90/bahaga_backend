package com.bahaga.booking.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Generamos una clave secreta segura de 256 bits
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método para generar el token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SECRET_KEY) // Usamos la clave segura generada
                .compact();
    }

    // Método para validar el token
    public String validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Usamos la misma clave para verificar la firma
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
