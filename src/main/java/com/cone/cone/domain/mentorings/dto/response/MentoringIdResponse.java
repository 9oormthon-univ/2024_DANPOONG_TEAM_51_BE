package com.cone.cone.domain.mentorings.dto.response;

public record MentoringIdResponse(
        Long mentoringId
) {
    public static MentoringIdResponse of(Long mentoringId) {
        return new MentoringIdResponse(mentoringId);
    }
}
