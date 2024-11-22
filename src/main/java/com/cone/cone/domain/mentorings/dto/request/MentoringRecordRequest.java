package com.cone.cone.domain.mentorings.dto.request;

import jakarta.validation.constraints.*;

public record MentoringRecordRequest(
        	@NotNull String fileName,
            @NotNull Integer duration
) {
}
