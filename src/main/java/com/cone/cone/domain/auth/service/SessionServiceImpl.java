package com.cone.cone.domain.auth.service;

import static com.cone.cone.global.constant.SessionConstant.ID;
import static com.cone.cone.global.constant.SessionConstant.ROLE;
import static com.cone.cone.global.constant.SessionConstant.SESSION_TIMEOUT;

import jakarta.servlet.http.*;
import org.springframework.stereotype.*;

@Service
public class SessionServiceImpl implements SessionService{
    @Override
    public HttpSession createSession(HttpServletRequest request, String id, Object role) {
        HttpSession newSession = request.getSession(true);

        newSession.setMaxInactiveInterval(SESSION_TIMEOUT);

        newSession.setAttribute(ID, id);
        newSession.setAttribute(ROLE, role);

        return newSession;
    }

    @Override
    public void regenerateSession(HttpServletRequest request, String id, Object role) {

        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession = createSession(request, id, role);
    }
}
