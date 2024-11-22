package com.cone.cone.domain.mentorings.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MentoringExceptionCode implements ExceptionCode {
    INVALID_MENTORING_REQUEST(HttpStatus.BAD_REQUEST, "멘토링에 대해 유효하지 않은 요청입니다");

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
