package com.cone.cone.domain.mentorings.dto.response;

import java.time.LocalDateTime;

public record MentoringTimeResponse(
        LocalDateTime time
) {
    public static MentoringTimeResponse of(LocalDateTime time) {
        return new MentoringTimeResponse(time);
    }
}
