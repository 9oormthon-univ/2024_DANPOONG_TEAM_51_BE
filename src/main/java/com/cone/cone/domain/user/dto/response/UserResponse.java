package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.Role;
import com.cone.cone.domain.user.entity.User;

public record UserResponse (
        Long id,
        String name,
        String profileImgUrl,
        Role role
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getProfileImgUrl(),
                user.getRole()
        );
    }
}