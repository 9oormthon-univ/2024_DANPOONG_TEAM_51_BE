package com.cone.cone.domain.auth.controller;

import static com.cone.cone.domain.user.code.AuthSuccessCode.SUCCESS_CHANGE_ROLE;
import static com.cone.cone.domain.user.code.AuthSuccessCode.SUCCESS_SOCIAL_LOGIN;

import com.cone.cone.domain.auth.service.*;
import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.annotation.*;
import com.cone.cone.global.response.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @PostMapping("/social/login")
    public ResponseEntity<ResponseTemplate<RoleResponse>> login(HttpServletRequest httpServletRequest, final @RequestBody @Valid LoginRequest request) {
        val response = authService.login(httpServletRequest, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_SOCIAL_LOGIN, response));
    }

    @PatchMapping("/onboarding")
    public ResponseEntity<ResponseTemplate<RoleResponse>> onboarding(HttpServletRequest httpServletRequest,
                                                                     final @SessionId Long userId,
                                                                     final @RequestBody @Valid RoleRequest request) {
        val response = authService.changeRole(httpServletRequest, userId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_CHANGE_ROLE, response));
    }

}
