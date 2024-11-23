package com.cone.cone.domain.mentorings.code;

import com.cone.cone.global.code.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MentoringSuccessCode implements SuccessCode {
    SUCCESS_REQUEST_MENTORING(HttpStatus.OK, "멘토링 신청에 성공했습니다"),
    SUCCESS_UPDATE_MENTORING_TIME(HttpStatus.OK, "멘토링 시간 예약에 성공했습니다"),
    SUCCESS_APPROVE_MENTORING(HttpStatus.OK, "멘토링 승인에 성공했습니다"),
    SUCCESS_REJECT_MENTORING(HttpStatus.OK, "멘토링 거절에 성공했습니다"),
    SUCCESS_GET_PRE_SIGNED_URL_FOR_MENTORING_RECORD(HttpStatus.OK, "멘토링 파일 업로드 url 조회에 성공했습니다"),
    SUCCESS_CREATE_MENTORING_CONTENT(HttpStatus.OK, "멘토링 기록에 성공했습니다");

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
