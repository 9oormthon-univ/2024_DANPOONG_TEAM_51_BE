package com.cone.cone.domain.user.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MentorSuccessCode implements SuccessCode {
    SUCCESS_GET_MENTOR_PROFILE(HttpStatus.OK, "멘토 프로필 조회에 성공했습니다"),
    SUCCESS_GET_MENTORS(HttpStatus.OK, "승인된 멘토 리스트 조회에 성공했습니다"),
    SUCCESS_GET_MENTORINGS_FOR_MENTOR(HttpStatus.OK, "멘토로 참여한 멘토링 목록 조회에 성공했습니다");

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
