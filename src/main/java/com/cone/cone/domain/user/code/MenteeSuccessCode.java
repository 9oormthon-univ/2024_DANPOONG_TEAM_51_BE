package com.cone.cone.domain.user.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MenteeSuccessCode implements SuccessCode {
    SUCCESS_GET_MENTEE_PROFILE(HttpStatus.OK, "멘티 프로필 조회에 성공했습니다");

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
