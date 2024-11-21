package com.cone.cone.global.config;

import com.cone.cone.global.annotation.*;
import java.util.*;
import org.springframework.context.annotation.*;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.config.annotation.*;

import static org.springframework.http.HttpHeaders.LOCATION;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionIdArgumentResolver sessionIdArgumentResolver;

    public WebConfig(SessionIdArgumentResolver sessionIdArgumentResolver) {
        this.sessionIdArgumentResolver = sessionIdArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionIdArgumentResolver);
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders(LOCATION);

        registry.addMapping("/auth/social/login")
                .allowedOrigins("*")
                .allowedMethods("POST", "OPTIONS")
                .allowCredentials(false);

        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
