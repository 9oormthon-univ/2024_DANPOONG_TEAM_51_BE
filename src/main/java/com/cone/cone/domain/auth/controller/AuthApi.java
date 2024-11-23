package com.cone.cone.domain.auth.controller;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.User;
import com.cone.cone.global.response.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import org.springframework.http.*;

public interface AuthApi {
    ResponseEntity<ResponseTemplate<AuthResponse>> login(HttpServletRequest httpServletRequest, @Valid LoginRequest request);

    ResponseEntity<ResponseTemplate<AuthResponse>> onboarding(HttpServletRequest httpServletRequest, Long userId,
                                                              @Valid RoleRequest request);

    ResponseEntity<ResponseTemplate<User>> getMe(HttpServletRequest httpServletRequest, Long id);
}
