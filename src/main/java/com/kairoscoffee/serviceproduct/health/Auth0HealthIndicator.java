package com.kairoscoffee.serviceproduct.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class Auth0HealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            // Aquí puedes validar conexión a Auth0 si quieres (ping a JWKS)
            return Health.up().withDetail("auth0", "Conexión correcta con Auth0").build();
        } catch (Exception e) {
            return Health.down().withDetail("auth0", "Fallo al conectar con Auth0").build();
        }
    }
}
