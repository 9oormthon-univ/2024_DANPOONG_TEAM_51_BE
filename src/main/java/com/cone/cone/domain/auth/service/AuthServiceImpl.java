package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.dto.request.LoginRequest;
import com.cone.cone.domain.user.dto.request.RoleRequest;
import com.cone.cone.domain.user.dto.response.RoleResponse;
import com.cone.cone.domain.user.dto.response.UserResponse;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.Role;
import com.cone.cone.domain.user.entity.User;
import com.cone.cone.domain.user.repository.MenteeRepository;
import com.cone.cone.domain.user.repository.MentorRepository;
import com.cone.cone.domain.user.repository.UserRepository;
import com.cone.cone.external.oauth.OAuthPlatformService;
import com.cone.cone.external.oauth.dto.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.cone.cone.domain.user.entity.Role.GUEST;

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
                    .name(userInfo.nickname())
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

    public RoleResponse updateRole(HttpServletRequest httpServletRequest, final Long userId, RoleRequest request) {
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

    public UserResponse getUserById(Long id) {
        val user = userRepository.findByIdOrThrow(id);
        return UserResponse.from(user);
    }
}
