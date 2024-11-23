package com.cone.cone.global.annotation;

import com.cone.cone.domain.user.entity.Role;
import com.cone.cone.global.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.util.Arrays;

import static com.cone.cone.global.code.CommonExceptionCode.AUTHENTICATION_REQUIRED;
import static com.cone.cone.global.code.CommonExceptionCode.INVALID_AUTHORITY;
import static com.cone.cone.global.constant.AuthConstant.ROLE;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizeAspect {
    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(SessionRole)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            throw new CustomException(AUTHENTICATION_REQUIRED);
        }
        Role role = (Role) session.getAttribute(ROLE);

        MethodSignature signature = (MethodSignature) (joinPoint.getSignature());
        Method method = signature.getMethod();
        SessionRole roleAnnotation = method.getAnnotation(SessionRole.class);
        Role[] roles = roleAnnotation.roles();

        boolean authorized = Arrays.asList(roles).contains(role);
        if (!authorized) {
            throw new CustomException(INVALID_AUTHORITY);
        }

        return joinPoint.proceed();
    }
}
