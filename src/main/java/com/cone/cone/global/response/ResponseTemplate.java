package com.cone.cone.global.response;

import com.cone.cone.global.code.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class ResponseTemplate<T> {

    private final boolean success;
    private final String message;
    private T data;

    public static ResponseTemplate success(SuccessCode type) {
        return new ResponseTemplate<>(true, type.message(), null);
    }

    public static <T> ResponseTemplate<T> success(SuccessCode type, T data) {
        return new ResponseTemplate<>(true, type.message(), data);
    }

    public static ResponseTemplate error(ExceptionCode type) {
        return new ResponseTemplate<>(false, type.message(), null);
    }
}
