package com.cone.cone.domain.mentorings.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MentoringRejectRequest(
        @NotBlank String rejectReason
        ) {
}
