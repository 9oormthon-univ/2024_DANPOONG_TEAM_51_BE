package com.cone.cone.global.config;

import com.cone.cone.global.annotation.SessionIdArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
                .allowedMethods("*")
                .allowCredentials(true)
                .exposedHeaders(LOCATION);

        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
