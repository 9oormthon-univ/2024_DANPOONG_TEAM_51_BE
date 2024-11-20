package com.cone.cone.domain.mentorings.dto.request;

import jakarta.validation.constraints.*;

public record MentoringRequest(
        @NotNull Long mentorId
) {
}
