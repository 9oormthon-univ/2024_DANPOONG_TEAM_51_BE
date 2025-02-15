package com.cone.cone.domain.user.code;

import com.cone.cone.global.code.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserExceptionCode implements ExceptionCode {
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "요청하신 사용자를 찾을 수 없습니다");

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
