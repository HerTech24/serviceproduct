package com.kairoscoffee.serviceproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Habilita @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        // =============================
                        //       ENDPOINTS PUBLICOS
                        // =============================
                        .requestMatchers(
                                "/api/productos",
                                "/api/productos/",
                                "/api/productos/*",
                                "/api/productos/ofertas/**",
                                "/api/productos/categoria/**",
                                "/api/productos/proveedor/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/categorias",
                                "/api/categorias/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/proveedores",
                                "/api/proveedores/**"
                        ).permitAll()

                        // =============================
                        //        SWAGGER PUBLICO
                        // =============================
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ACTUATOR health público
                        .requestMatchers("/actuator/health").permitAll()

                        // ========================================
                        //   TODO LO DEMÁS → REQUIERE AUTENTICACIÓN
                        // ========================================
                        .anyRequest().authenticated()
                )

                // Resource Server para JWT (Auth0)
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(Customizer.withDefaults())
                );

        return http.build();
    }
}
