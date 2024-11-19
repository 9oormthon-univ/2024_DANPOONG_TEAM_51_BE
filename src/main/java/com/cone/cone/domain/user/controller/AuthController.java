package com.cone.cone.domain.user.controller;

import static com.cone.cone.domain.user.code.AuthSuccessCode.SUCCESS_SOCIAL_LOGIN;

import com.cone.cone.domain.auth.service.*;
import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.service.*;
import com.cone.cone.global.response.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi{
    private final AuthService authService;

    @PostMapping("/social/login")
    public ResponseEntity<ResponseTemplate<RoleResponse>> login(final @RequestBody @Valid LoginRequest request) {
        val response = authService.login(request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_SOCIAL_LOGIN, response));
    }

}
