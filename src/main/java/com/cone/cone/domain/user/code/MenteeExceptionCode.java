package com.cone.cone.domain.user.code;

import com.cone.cone.global.code.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MenteeExceptionCode implements ExceptionCode {
    INVALID_REQUEST_FIND_MENTEE(HttpStatus.BAD_REQUEST, "요청하신 멘티를 찾을 수 없습니다");

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
