package com.cone.cone.domain.auth.service;

import static com.cone.cone.domain.user.entity.Role.GUEST;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import com.cone.cone.external.oauth.*;
import com.cone.cone.external.oauth.dto.*;
import com.cone.cone.global.exception.*;
import jakarta.servlet.http.*;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;
    private final OAuthPlatformService oAuthPlatformService;
    private final SessionService sessionService;

    public RoleResponse login(HttpServletRequest httpServletRequest, LoginRequest request) {
        UserInfoResponse userInfo = oAuthPlatformService.getUserInfo(request.platformType(), request.code());
        Optional<User> existingUser = userRepository.findByPlatformId(userInfo.id());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            createSession(httpServletRequest, user);
            return RoleResponse.of(user.getRole());
        } else {
            User newUser = User.builder()
                    .role(GUEST)
                    .platformType(request.platformType())
                    .platformId(userInfo.id())
                    .username(userInfo.nickname())
                    .profileImgUrl(userInfo.profileUrl())
                    .build();
            userRepository.save(newUser);
            createSession(httpServletRequest, newUser);
            return new RoleResponse(newUser.getRole());
        }
    }

    private void createSession(HttpServletRequest httpServletRequest, User user) {
        sessionService.generateSession(httpServletRequest, user.getId(), user.getRole());
    }

    public RoleResponse changeRole(HttpServletRequest httpServletRequest, final Long userId, RoleRequest request) {
        val user = userRepository.findByIdOrThrow(userId);
        val role = request.role();

        Role.validateRole(role);
        user.changeRole(role);
        switch (role) {
            case MENTEE -> menteeRepository.save(createMentee(user));
            case MENTOR -> mentorRepository.save(createMentor(user));
        }
        sessionService.generateSession(httpServletRequest, userId, user.getRole());
        return RoleResponse.of(user.getRole());
    }

    private Mentee createMentee(User user) {
        return Mentee.builder()
                .user(user)
                .build();
    }

    private Mentor createMentor(User user) {
        return Mentor.builder()
                .user(user)
                .build();
    }
}
