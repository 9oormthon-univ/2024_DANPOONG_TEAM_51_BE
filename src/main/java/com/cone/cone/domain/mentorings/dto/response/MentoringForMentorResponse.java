package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.entity.Mentee;

import java.util.List;

public record MentoringForMentorResponse(
        Long id,
        Long menteeId,
        String menteeUsername,
        String menteeProfileImgUrl,
        String concernSummary,
        String concernDetail,
        String mentorPreference,
        List<String> keywords

) {
    public static MentoringForMentorResponse of(Long id, Mentee mentee) {
        return new MentoringForMentorResponse(
                id,
                mentee.getId(),
                mentee.getName(),
                mentee.getProfileImgUrl(),
                mentee.getConcernSummary(),
                mentee.getConcernDetail(),
                mentee.getMentorPreference(),
                mentee.getKeywords()
        );
    }
}
