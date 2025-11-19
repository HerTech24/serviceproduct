package com.kairoscoffee.serviceproduct.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JwtAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("🔍 [JWT Filter] Procesando request: " + request.getRequestURI());
        System.out.println("🔍 [JWT Filter] Authorization Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("⚠️ [JWT Filter] No hay token Bearer, continuando sin autenticación");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("🔍 [JWT Filter] Token extraído (primeros 50 chars): " + token.substring(0, Math.min(50, token.length())));

        try {
            String username = jwtService.extractUsername(token);
            System.out.println("✅ [JWT Filter] Username extraído: " + username);

            var roles = jwtService.extractRoles(token);
            System.out.println("✅ [JWT Filter] Roles extraídos: " + roles);

            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            System.out.println("✅ [JWT Filter] Authorities creadas: " + authorities);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("✅ [JWT Filter] Autenticación establecida en SecurityContext");
            System.out.println("✅ [JWT Filter] Usuario: " + auth.getName() + " | Authorities: " + auth.getAuthorities());

        } catch (Exception e) {
            System.out.println("❌ [JWT Filter] Error procesando token: " + e.getClass().getName());
            System.out.println("❌ [JWT Filter] Mensaje de error: " + e.getMessage());
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}