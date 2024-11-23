package com.cone.cone.global.security.filter;

import com.cone.cone.global.exception.*;
import com.cone.cone.global.response.*;
import com.fasterxml.jackson.databind.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (CustomException e) {
      response.setStatus(e.getHttpStatus());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      String error = objectMapper.writeValueAsString(ResponseTemplate.error(e.getExceptionCode()));
      response.getWriter().write(error);
    }
  }
}
