package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.dto.response.MentorResponse;
import com.cone.cone.domain.user.entity.Mentor;

public record MentoringForMenteeResponse(
        Long id,
        MentorResponse mentor
) {
    public static MentoringForMenteeResponse of(Long id, Mentor mentor) {
        return new MentoringForMenteeResponse(
                id,
                MentorResponse.from(mentor)
        );
    }
}
