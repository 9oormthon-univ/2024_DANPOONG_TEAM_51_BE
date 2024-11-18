package com.cone.cone.global.code;

import org.springframework.http.*;

public interface SuccessCode {
    HttpStatus status();

    String message();
}
