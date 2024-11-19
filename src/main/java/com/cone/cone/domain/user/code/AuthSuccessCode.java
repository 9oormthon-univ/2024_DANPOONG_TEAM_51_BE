package com.cone.cone.domain.user.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthSuccessCode implements SuccessCode {
    SUCCESS_SOCIAL_LOGIN(HttpStatus.OK, "소셜 로그인에 성공했습니다"),
    SUCCESS_CHANGE_ROLE(HttpStatus.OK, "역할 변경에 성공했습니다");

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
