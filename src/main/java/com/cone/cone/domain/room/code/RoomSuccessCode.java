package com.cone.cone.domain.room.code;

import com.cone.cone.global.code.SuccessCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoomSuccessCode implements SuccessCode {
    SUCCESS_CREATE_ROOM(HttpStatus.OK, "멘토링 방 생성에 성공하셨습니다"),
    SUCCESS_GET_ROOMS(HttpStatus.OK, "멘토링 방 목록 조회에 성공하셨습니다");

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
