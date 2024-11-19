package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import jakarta.validation.constraints.*;

public record RoleResponse(
        @NotNull Role role
){
    public static RoleResponse of(Role role) {
        return new RoleResponse(role);
    }
}
