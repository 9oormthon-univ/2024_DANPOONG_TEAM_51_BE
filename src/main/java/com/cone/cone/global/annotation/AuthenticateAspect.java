package com.cone.cone.global.annotation;

import com.cone.cone.global.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import static com.cone.cone.global.code.CommonExceptionCode.AUTHENTICATION_REQUIRED;
import static com.cone.cone.global.code.CommonExceptionCode.TRY_LOGIN_AGAIN;
import static com.cone.cone.global.constant.SessionConstant.ID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticateAspect {
    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(SessionAuth)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            throw new CustomException(TRY_LOGIN_AGAIN);
        }

        Long id = (Long) session.getAttribute(ID);
        if (id == null) {
            throw new CustomException(AUTHENTICATION_REQUIRED);
        }

        return joinPoint.proceed();
    }
}
