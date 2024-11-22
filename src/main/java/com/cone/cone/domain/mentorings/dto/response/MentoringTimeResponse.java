package com.cone.cone.domain.mentorings.dto.response;

import java.time.LocalDateTime;

public record MentoringTimeResponse(
        LocalDateTime mentoringTime
) {
    public static MentoringTimeResponse of(LocalDateTime mentoringTime) {
        return new MentoringTimeResponse(mentoringTime);
    }
}
