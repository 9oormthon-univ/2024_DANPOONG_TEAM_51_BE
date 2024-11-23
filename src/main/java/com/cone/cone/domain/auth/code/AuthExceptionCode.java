package com.cone.cone.domain.auth.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionCode implements ExceptionCode {
    INVALID_TOKEN_HEADER(HttpStatus.BAD_REQUEST, "토큰 헤더가 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "서비스에서 발급되지 않은 토큰입니다."),
    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "기한이 만료된 토큰입니다."),
    INVALID_REQUEST_LOGIN(HttpStatus.BAD_REQUEST, "올바르지 않은 로그인 요청입니다"),
    INVALID_REQUEST_SOCIAL_LOGIN(HttpStatus.BAD_REQUEST, "올바르지 않은 소셜 로그인 요청입니다"),
    INVALID_TOKEN_USE(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다")
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }
}
