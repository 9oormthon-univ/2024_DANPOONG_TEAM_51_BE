package com.cone.cone.domain.messages.code;

import com.cone.cone.global.code.SuccessCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MessageSuccessCode implements SuccessCode {
    SUCCESS_GET_MESSAGES(HttpStatus.OK, "메시지 목록 조회에 성공하셨습니다");

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
