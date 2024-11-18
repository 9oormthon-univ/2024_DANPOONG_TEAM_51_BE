package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi{

    public ResponseEntity<ResponseTemplate<LoginResponse>> login(final @Valid LoginRequest request) {
        return null;
    }

}
