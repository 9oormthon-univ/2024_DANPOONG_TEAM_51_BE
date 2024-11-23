package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import static com.cone.cone.global.constant.SessionConstant.*;

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
