package com.cone.cone.domain.auth.service;

import jakarta.servlet.http.*;

public interface SessionService {
    HttpSession createSession(HttpServletRequest request, String id, Object role);

    void regenerateSession(HttpServletRequest request, String id, Object role);
}
