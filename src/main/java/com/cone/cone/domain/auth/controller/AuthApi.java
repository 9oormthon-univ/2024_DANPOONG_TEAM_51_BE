package com.cone.cone.domain.auth.controller;

import com.cone.cone.domain.user.dto.request.LoginRequest;
import com.cone.cone.domain.user.dto.request.RoleRequest;
import com.cone.cone.domain.user.dto.response.RoleResponse;
import com.cone.cone.domain.user.entity.User;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AuthApi {
    ResponseEntity<ResponseTemplate<RoleResponse>> login(HttpServletRequest httpServletRequest, @Valid LoginRequest request);

    ResponseEntity<ResponseTemplate<RoleResponse>> onboarding(HttpServletRequest httpServletRequest, Long userId, @Valid RoleRequest request);

    ResponseEntity<ResponseTemplate<User>> getMyInfo(HttpServletRequest httpServletRequest, Long id);
}
