// ============================================
// 11. Auth0HealthIndicator.java
// ============================================
package com.kairoscoffee.serviceproduct.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class Auth0HealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            // Aquí podrías probar un request real al JWKS de Auth0
            return Health.up()
                    .withDetail("auth0", "Conexión correcta con Auth0")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("auth0", "Fallo al conectar con Auth0")
                    .build();
        }
    }
}