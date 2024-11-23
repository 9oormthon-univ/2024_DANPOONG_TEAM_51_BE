package com.cone.cone.domain.auth.service;

import static com.cone.cone.domain.user.entity.Role.GUEST;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import com.cone.cone.external.jwt.*;
import com.cone.cone.external.oauth.*;
import com.cone.cone.external.oauth.dto.*;
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
    private final JwtService jwtService;

    public AuthResponse login(HttpServletRequest httpServletRequest, LoginRequest request) {
        UserInfoResponse userInfo = oAuthPlatformService.getUserInfo(request.platformType(), request.code());
        Optional<User> existingUser = userRepository.findByPlatformId(userInfo.id());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            val issueToken = jwtService.issueToken(user.getId(), List.of(user.getRole().name()));
            return new AuthResponse(user.getId(), user.getRole(), issueToken.accessToken());
        } else {
            User newUser = User.builder()
                    .role(GUEST)
                    .platformType(request.platformType())
                    .platformId(userInfo.id())
                    .username(userInfo.nickname())
                    .profileImgUrl(userInfo.profileUrl())
                    .build();
            userRepository.save(newUser);
            val issueToken = jwtService.issueToken(newUser.getId(), List.of(newUser.getRole().name()));
            return new AuthResponse(newUser.getId(), newUser.getRole(), issueToken.accessToken());
        }
    }

    public AuthResponse changeRole(HttpServletRequest httpServletRequest, final Long userId, RoleRequest request) {
        val user = userRepository.findByIdOrThrow(userId);
        val role = request.role();

        Role.validateRole(role);
        user.changeRole(role);
        switch (role) {
            case MENTEE -> menteeRepository.save(createMentee(user));
            case MENTOR -> mentorRepository.save(createMentor(user));
        }
        val issueToken = jwtService.issueToken(user.getId(), List.of(user.getRole().name()));
        return new AuthResponse(user.getId(), user.getRole(), issueToken.accessToken());
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

    public User getUserById(Long id) {
        return userRepository.findByIdOrThrow(id);
    }
}
