package com.cone.cone.external.code;

import com.cone.cone.global.code.ExceptionCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientExceptionCode implements ExceptionCode {
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 인증 코드입니다."),
    // 사용자의 인증 코드가 유효한데, 액세스 코드가 유효하지 않을 경우 내부 서버의 문제
    INVALID_AUTH_ACCESS_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "유효하지 않은 인증 액세스 코드입니다");

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