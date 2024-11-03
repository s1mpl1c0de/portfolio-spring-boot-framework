package com.simplicode.portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
           .title("Portfolio Spring Boot Framework")
           .description("API Documentation for portfolio project")
           .version("1.0.0");

        return new OpenAPI().info(info);
    }

}
