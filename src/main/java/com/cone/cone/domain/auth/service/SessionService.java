package com.cone.cone.domain.auth.service;

import jakarta.servlet.http.*;

public interface SessionService {
    HttpSession createSession(HttpServletRequest request, Long id, Object role);

    void regenerateSession(HttpServletRequest request, Long id, Object role);
}
