package com.cone.cone.domain.user.dto.request;

import com.cone.cone.domain.user.entity.*;
import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotNull PlatformType platformType,
        @NotNull @Size(max = 150) String code
) {
}
