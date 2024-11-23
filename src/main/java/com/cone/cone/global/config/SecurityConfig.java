package com.cone.cone.global.config;

import com.cone.cone.global.security.filter.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.factory.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  public static final String[] AUTH_WHITELIST = {
          "/", "/error", "/favicon.ico", "/actuator/health", "/recommend/best-seller"
  };

  public static final String[] AUTH_WHITELIST_WILDCARD = {
          "/webjars/**",
          "/swagger-resources/**",
          "/swagger-ui/**",
          "/v3/api-docs/**",
          "/webjars/**",
          "/auth/**",
          "/css/**",
          "/images/**",
          "/js/**",
          "/h2-console/**",
          "/test/**",
          "/books/**"
  };

  @Value("${spring.web.origin.server}")
  private String serverOrigin;

  @Value("${spring.web.origin.client}")
  private String clientOrigin;

  @Value("${spring.web.origin.client-local}")
  private String clientLocalOrigin;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins(serverOrigin, clientOrigin, clientLocalOrigin)
                .allowedOriginPatterns(serverOrigin, clientOrigin, clientLocalOrigin)
                .allowedHeaders("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name());
      }
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(
                    headerConfig ->
                            headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

    http.authorizeHttpRequests(
                    auth -> {
                      auth.requestMatchers(AUTH_WHITELIST).permitAll();
                      auth.requestMatchers(AUTH_WHITELIST_WILDCARD).permitAll();
                      auth.anyRequest().authenticated();
                    })
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}