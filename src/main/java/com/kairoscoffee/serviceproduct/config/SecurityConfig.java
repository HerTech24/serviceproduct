// ============================================
// 1. SecurityConfig.java
// ============================================
package com.kairoscoffee.serviceproduct.config;

import com.kairoscoffee.serviceproduct.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // 📌 Rutas públicas
                        .requestMatchers("/product/public/**").permitAll()

                        // 📌 Actuator
                        .requestMatchers("/actuator/**").permitAll()

                        // 📌 Swagger
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // 📌 Rutas USER/ADMIN
                        .requestMatchers("/product/user/**")
                        .hasAnyAuthority("CUSTOMER", "ADMIN")

                        // 📌 Rutas solo ADMIN (CRUD)
                        .requestMatchers("/product/admin/**")
                        .hasAuthority("ADMIN")

                        // 📌 El resto requiere autenticación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}