package com.cone.cone.domain.mentorings.dto.request;

import jakarta.validation.constraints.*;

public record MentoringRecordRequest(
		@NotNull @Size(max = 100) String fileName,
        @NotNull int duration
) {
}
