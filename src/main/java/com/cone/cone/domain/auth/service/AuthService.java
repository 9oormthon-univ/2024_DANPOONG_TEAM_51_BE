package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;

public interface AuthService {
    RoleResponse login(LoginRequest request);

    RoleResponse changeRole(RoleRequest request);
}
