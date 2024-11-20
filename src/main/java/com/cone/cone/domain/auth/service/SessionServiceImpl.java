package com.cone.cone.domain.auth.service;

import static com.cone.cone.global.constant.SessionConstant.ID;
import static com.cone.cone.global.constant.SessionConstant.ROLE;
import static com.cone.cone.global.constant.SessionConstant.SESSION_TIMEOUT;

import com.cone.cone.domain.user.entity.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.*;

@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public void generateSession(HttpServletRequest request, Long id, Role role) {
        HttpSession oldSession = request.getSession(false);

        if (oldSession != null) {
            oldSession.invalidate();
        }
        createSession(request, id, role);
    }

    private void createSession(HttpServletRequest request, Long id, Role role) {
        HttpSession newSession = request.getSession(true);

        newSession.setMaxInactiveInterval(SESSION_TIMEOUT);
        newSession.setAttribute(ID, id);
        newSession.setAttribute(ROLE, role);
    }
}
