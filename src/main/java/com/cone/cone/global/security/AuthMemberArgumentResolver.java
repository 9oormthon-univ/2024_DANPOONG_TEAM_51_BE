package com.cone.cone.global.security;

import org.springframework.core.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

@Component
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {
  private static final String ANONYMOUS_USER = "anonymousUser";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasAuthMemberAnnotation = parameter.getParameterAnnotation(AuthUser.class) != null;
    boolean isMemberIdString = parameter.getParameterType().equals(Long.class);
    return hasAuthMemberAnnotation && isMemberIdString;
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    boolean isNotAuthenticatedMember = authentication.getPrincipal().equals(ANONYMOUS_USER);
    if (isNotAuthenticatedMember) {
      return null;
    }
    return Long.valueOf((String) authentication.getPrincipal());
  }
}
