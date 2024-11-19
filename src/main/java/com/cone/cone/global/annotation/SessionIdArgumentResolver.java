package com.cone.cone.global.annotation;

import static com.cone.cone.global.code.CommonExceptionCode.AUTHENTICATION_REQUIRED;

import com.cone.cone.global.exception.*;
import jakarta.servlet.http.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

@Component
public class SessionIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SessionId.class)
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpSession session = webRequest.getNativeRequest(jakarta.servlet.http.HttpServletRequest.class).getSession(false);
        if (session != null) {
            Long userId = (Long) session.getAttribute("id");
            if (userId != null) {
                return userId;
            } else {
                throw new CustomException(AUTHENTICATION_REQUIRED);
            }
        }
        return null;
    }
}
