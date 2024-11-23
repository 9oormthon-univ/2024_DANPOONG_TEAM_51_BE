package com.cone.cone.domain.auth.controller;

import static com.cone.cone.domain.auth.code.AuthSuccessCode.*;
import static com.cone.cone.domain.user.entity.Role.GUEST;

import com.cone.cone.domain.auth.service.*;
import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.*;
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

    @SessionAuth
    @PatchMapping("/onboarding")
    public ResponseEntity<ResponseTemplate<RoleResponse>> onboarding(HttpServletRequest httpServletRequest,
                                                                     final @SessionId Long userId,
                                                                     final @RequestBody @Valid RoleRequest request) {
        val response = authService.changeRole(httpServletRequest, userId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_CHANGE_ROLE, response));
    }

    @SessionAuth
    @GetMapping("/me")
    public ResponseEntity<ResponseTemplate<User>> getMe(
            final HttpServletRequest httpServletRequest,
            final @SessionId Long id
    ) {
        val response = authService.getUserById(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_USER, response));
    }

}
