package com.cone.cone.global.config;

import com.cone.cone.global.annotation.*;
import java.util.*;
import org.springframework.context.annotation.*;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.config.annotation.*;

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
}