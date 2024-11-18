package com.cone.cone.global.exception;

import static com.cone.cone.global.code.CommonExceptionCode.INTERNAL_SERVER_ERROR;
import static com.cone.cone.global.code.CommonExceptionCode.INVALID_INPUT_VALUE;
import static com.cone.cone.global.code.CommonExceptionCode.INVALID_JSON_TYPE;
import static com.cone.cone.global.code.CommonExceptionCode.INVALID_REQUEST_PARAM_TYPE;
import static com.cone.cone.global.code.CommonExceptionCode.NOT_FOUND_PATH;
import static com.cone.cone.global.code.CommonExceptionCode.NOT_NULL_REQUEST_PARAM;

import com.cone.cone.global.response.*;
import java.io.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseTemplate> handleCustomException(CustomException ex) {
        log.error(
                "🚨CustomException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
        return ResponseEntity.status(ex.getExceptionCode().status())
                .body(ResponseTemplate.error(ex.getExceptionCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseTemplate> handleServerException(RuntimeException ex) {
        log.error(
                "🚨 InternalException occurred: {} 🚨\n{}", ex.getMessage(), getStackTraceAsString(ex));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseTemplate.error(INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseTemplate> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.error(NOT_FOUND_PATH));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseTemplate> handleValidationError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseTemplate.error(INVALID_INPUT_VALUE));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseTemplate> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseTemplate.error(INVALID_REQUEST_PARAM_TYPE));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseTemplate> handleMissingServletRequestParameterException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseTemplate.error(NOT_NULL_REQUEST_PARAM));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseTemplate> handleHttpMediaTypeNotSupportedException() {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(ResponseTemplate.error(INVALID_JSON_TYPE));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseTemplate> handleHttpMessageNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseTemplate.error(INVALID_INPUT_VALUE));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseTemplate> handleHandlerMethodValidationException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseTemplate.error(NOT_NULL_REQUEST_PARAM));
    }

    private String getStackTraceAsString(RuntimeException ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
