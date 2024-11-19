package com.cone.cone.domain.room.code;

import com.cone.cone.global.code.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoomExceptionCode implements ExceptionCode {
    // 400 Bad Request
    ALREADY_EXIST_ROOM(HttpStatus.BAD_REQUEST, "이미 존재하는 멘토링 방입니다"),

    // 404 Not Found
    NOT_FOUND_ROOM(HttpStatus.NOT_FOUND, "요청하신 멘토링 방을 찾을 수 없습니다");

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