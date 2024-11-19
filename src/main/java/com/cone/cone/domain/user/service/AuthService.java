package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;

public interface AuthService {
    RoleResponse login(LoginRequest request);
}
