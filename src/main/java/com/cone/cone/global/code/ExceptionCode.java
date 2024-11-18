package com.cone.cone.global.code;

import org.springframework.http.*;

public interface ExceptionCode {
    HttpStatus status();

    String message();
}
