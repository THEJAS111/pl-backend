package com.pl.premier_zone; // IMPORTANT: Verify this package matches your project structure

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Apply CORS to all routes starting with /api/
        // Adjust to "/**" if your endpoints don't start with /api
        registry.addMapping("/api/**")
                // Allow requests from your React development server
                .allowedOrigins("http://localhost:5174")
                // Allow common HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Allow all request headers
                .allowedHeaders("*")
                // Allow credentials (like cookies and Authorization headers)
                .allowCredentials(true);
    }
}
