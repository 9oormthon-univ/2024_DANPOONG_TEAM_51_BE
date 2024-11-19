package com.cone.cone.domain.auth.service;

import com.cone.cone.domain.user.dto.request.*;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import com.cone.cone.external.oauth.*;
import com.cone.cone.external.oauth.dto.*;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final OAuthPlatformService oAuthPlatformService;
    private final UserRepository userRepository;

    public RoleResponse login(LoginRequest request) {
        UserInfoResponse userInfo = oAuthPlatformService.getUserInfo(request.platformType(), request.code());
        Optional<User> existingUser = userRepository.findByPlatformId(userInfo.id());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            return RoleResponse.of(user.getRole());
        } else {
            User newUser = User.builder()
                    .role(Role.GUEST)
                    .platformType(request.platformType())
                    .platformId(userInfo.id())
                    .build();
            userRepository.save(newUser);
            return new RoleResponse(newUser.getRole());
        }
    }

    public RoleResponse changeRole(final RoleRequest request) {
        return null;
    }
}
