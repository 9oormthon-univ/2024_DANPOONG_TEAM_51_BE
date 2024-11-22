package com.cone.cone.domain.mentorings.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MentoringExceptionCode implements ExceptionCode {
    INVALID_REQUEST_FIND_MENTORING(HttpStatus.BAD_REQUEST, "요청하신 멘토링을 찾을 수 없습니다"),
    INVALID_REQUEST_USER(HttpStatus.BAD_REQUEST, "사용자가 참여하지 않은 멘토링입니다"),
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
