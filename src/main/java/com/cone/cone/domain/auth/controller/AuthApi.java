package com.cone.cone.domain.auth.controller;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import org.springframework.http.*;

public interface AuthApi {
    ResponseEntity<ResponseTemplate<RoleResponse>> login(HttpServletRequest httpServletRequest, @Valid LoginRequest request);
}
