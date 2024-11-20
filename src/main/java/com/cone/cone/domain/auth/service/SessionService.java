package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.entity.*;
import jakarta.servlet.http.*;

public interface SessionService {
    void generateSession(HttpServletRequest request, Long id, Role role);
}
