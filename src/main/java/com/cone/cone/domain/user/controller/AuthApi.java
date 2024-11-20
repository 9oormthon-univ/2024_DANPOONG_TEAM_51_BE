package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.*;
import jakarta.validation.*;
import org.springframework.http.*;

public interface AuthApi {
    ResponseEntity<ResponseTemplate<LoginResponse>> login(@Valid LoginRequest request);
}