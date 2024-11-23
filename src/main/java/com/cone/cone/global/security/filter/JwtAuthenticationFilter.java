package com.cone.cone.global.security.filter;

import static com.cone.cone.domain.auth.code.AuthExceptionCode.INVALID_TOKEN_USE;
import static com.cone.cone.global.config.SecurityConfig.AUTH_WHITELIST;
import static com.cone.cone.global.config.SecurityConfig.AUTH_WHITELIST_WILDCARD;

import com.cone.cone.external.jwt.*;
import com.cone.cone.global.exception.*;
import com.cone.cone.global.security.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenValidator jwtTokenValidator;
  private final JwtService jwtService;
  private static final int START_WILDCARD_INDEX = 0;
  private static final int END_WILDCARD_INDEX = 3;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestUri = request.getRequestURI();

    if (isWhitelisted(requestUri)) {
      filterChain.doFilter(request, response);
      return;
    }

    handleTokenAuthentication(request, response, filterChain);
  }

  private boolean isWhitelisted(String requestUri) {
    return Arrays.asList(AUTH_WHITELIST).contains(requestUri)
        || Arrays.stream(AUTH_WHITELIST_WILDCARD)
            .anyMatch(
                whiteUrl ->
                    requestUri.startsWith(
                        whiteUrl.substring(
                            START_WILDCARD_INDEX, whiteUrl.length() - END_WILDCARD_INDEX)));
  }

  private void handleAIRecommendPath(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      authenticateRequest(request);
    } catch (CustomException e) {
      SecurityContextHolder.getContext().setAuthentication(createAnonymousAuthentication());
    }
    filterChain.doFilter(request, response);
  }

  private UserAuthentication createAnonymousAuthentication() {
    Collection<? extends GrantedAuthority> authorities =
        List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    return new UserAuthentication("anonymousUser", null, authorities); // 익명 유저 생성
  }

  private void handleTokenAuthentication(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      authenticateRequest(request);
    } catch (RuntimeException e) {
      throw new CustomException(INVALID_TOKEN_USE);
    }
    filterChain.doFilter(request, response);
  }

  private void authenticateRequest(HttpServletRequest request) {
    String accessToken = jwtService.extractAccessToken(request);
    jwtTokenValidator.validateToken(accessToken);

    String memberId = jwtService.getMemberIdFromAccessToken(accessToken);
    List<String> roles = jwtService.getRolesFromAccessToken(accessToken);
    Collection<? extends GrantedAuthority> authorities = getAuthoritiesFromList(roles);
    UserAuthentication memberAuthentication =
        new UserAuthentication(memberId, null, authorities);

    log.info("Authentication Principal : {}", memberAuthentication.getPrincipal());

    SecurityContextHolder.getContext().setAuthentication(memberAuthentication);
  }

  private Collection<? extends GrantedAuthority> getAuthoritiesFromList(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
