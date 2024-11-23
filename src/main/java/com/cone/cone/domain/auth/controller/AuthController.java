package com.cone.cone.domain.auth.controller;

import com.cone.cone.domain.auth.service.AuthService;
import com.cone.cone.domain.user.dto.request.LoginRequest;
import com.cone.cone.domain.user.dto.request.RoleRequest;
import com.cone.cone.domain.user.dto.response.RoleResponse;
import com.cone.cone.domain.user.dto.response.UserResponse;
import com.cone.cone.global.annotation.SessionAuth;
import com.cone.cone.global.annotation.SessionId;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cone.cone.domain.auth.code.AuthSuccessCode.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @PostMapping("/social/login")
    public ResponseEntity<ResponseTemplate<RoleResponse>> login(
            final HttpServletRequest httpServletRequest,
            final @RequestBody @Valid LoginRequest request
    ) {
        val response = authService.login(httpServletRequest, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_SOCIAL_LOGIN, response));
    }

    @SessionAuth
    @PatchMapping("/onboarding")
    public ResponseEntity<ResponseTemplate<RoleResponse>> onboarding(
            HttpServletRequest httpServletRequest,
            final @SessionId Long userId,
            final @RequestBody @Valid RoleRequest request
    ) {
        val response = authService.updateRole(httpServletRequest, userId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_CHANGE_ROLE, response));
    }

    @SessionAuth
    @GetMapping("/me")
    public ResponseEntity<ResponseTemplate<UserResponse>> getMyInfo(
            final HttpServletRequest httpServletRequest,
            final @SessionId Long id
    ) {
        val response = authService.getUserById(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_USER, response));
    }

}
