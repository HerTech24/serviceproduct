package com.kairoscoffee.serviceproduct.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Kairos Coffee - Service Product API",
                version = "1.0",
                description = "Documentación del microservicio de productos"
        )
)
public class SwaggerConfig {
}
