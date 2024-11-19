package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import jakarta.servlet.http.*;

public interface AuthService {
    RoleResponse login(HttpServletRequest httpServletRequest, LoginRequest request);

    RoleResponse changeRole(HttpServletRequest httpServletRequest, RoleRequest request);
}
