package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import jakarta.validation.constraints.*;

public record LoginResponse(
        @NotNull Role role
){
}
