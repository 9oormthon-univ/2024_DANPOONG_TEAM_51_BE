package com.cone.cone.domain.mentorings.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MentoringRejectRequest(
        @NotBlank @Size(max = 255) String rejectReason
        ) {
}
