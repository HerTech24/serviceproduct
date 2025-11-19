package com.kairoscoffee.serviceproduct.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    private Algorithm getAlgorithm() {
        System.out.println("🔑 [JWT Service] Usando secret (primeros 10 chars): " + secret.substring(0, Math.min(10, secret.length())));
        return Algorithm.HMAC256(secret);
    }

    /**
     * Obtener email desde "sub"
     */
    public String extractUsername(String token) {
        DecodedJWT decoded = decode(token);
        String username = decoded.getSubject();
        System.out.println("👤 [JWT Service] Username desde 'sub': " + username);
        return username;
    }

    /**
     * Obtener rol desde "role" (alineado con AuthService)
     */
    public List<String> extractRoles(String token) {
        DecodedJWT decoded = decode(token);

        // Imprimir todos los claims disponibles
        System.out.println("📋 [JWT Service] Claims disponibles en el token:");
        decoded.getClaims().forEach((key, claim) -> {
            System.out.println("   - " + key + ": " + claim.asString());
        });

        String role = decoded.getClaim("role").asString();
        System.out.println("🎭 [JWT Service] Role extraído del claim 'role': " + role);

        if (role == null || role.isBlank()) {
            System.out.println("❌ [JWT Service] El claim 'role' está vacío o null");
            throw new IllegalArgumentException("El token NO contiene claim 'role'");
        }

        System.out.println("✅ [JWT Service] Retornando roles: [" + role + "]");
        return List.of(role);
    }

    /**
     * Decodifica y valida el token JWT
     */
    private DecodedJWT decode(String token) {
        try {
            System.out.println("🔓 [JWT Service] Intentando decodificar token...");
            DecodedJWT decoded = JWT.require(getAlgorithm())
                    .build()
                    .verify(token);
            System.out.println("✅ [JWT Service] Token decodificado exitosamente");
            System.out.println("   - Issuer: " + decoded.getIssuer());
            System.out.println("   - Subject: " + decoded.getSubject());
            System.out.println("   - Expires at: " + decoded.getExpiresAt());
            return decoded;
        } catch (Exception e) {
            System.out.println("❌ [JWT Service] Error al decodificar token: " + e.getMessage());
            throw e;
        }
    }
}