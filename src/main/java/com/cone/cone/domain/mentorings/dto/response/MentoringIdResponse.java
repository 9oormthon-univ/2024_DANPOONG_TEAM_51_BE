package com.cone.cone.domain.mentorings.dto.response;

public record MentoringIdResponse(
        long mentoringId
) {
    public static MentoringIdResponse of (long mentoringId) {
        return new MentoringIdResponse(mentoringId);
    }
}
