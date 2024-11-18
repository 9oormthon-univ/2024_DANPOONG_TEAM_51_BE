package com.cone.cone.global.exception;

import com.cone.cone.global.code.*;
import lombok.*;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.message());
        this.exceptionCode = exceptionCode;
    }

    public int getHttpStatus() {
        return exceptionCode.status().value();
    }
}
