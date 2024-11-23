package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import jakarta.validation.constraints.*;

public record AuthResponse(
        @NotNull Long id,
        @NotNull Role role,
        @NotNull String accessToken
){
    public static AuthResponse of(Long id, Role role, String accessToken) {
        return new AuthResponse(id, role, accessToken);
    }
}
