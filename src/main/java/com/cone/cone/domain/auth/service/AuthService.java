package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.User;
import jakarta.servlet.http.*;

public interface AuthService {
    AuthResponse login(HttpServletRequest httpServletRequest, LoginRequest request);

    AuthResponse changeRole(HttpServletRequest httpServletRequest, Long userId, RoleRequest request);

    User getUserById(Long id);
}
