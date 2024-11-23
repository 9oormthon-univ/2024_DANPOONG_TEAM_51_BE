package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.dto.response.MenteeResponse;
import com.cone.cone.domain.user.entity.Mentee;

public record MentoringForMentorResponse(
        Long id,
        MenteeResponse mentee

) {
    public static MentoringForMentorResponse of(Long id, Mentee mentee) {
        return new MentoringForMentorResponse(
                id,
                MenteeResponse.from(mentee)
        );
    }
}
