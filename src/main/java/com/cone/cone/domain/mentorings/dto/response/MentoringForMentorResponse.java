package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.entity.Mentee;

import java.util.List;

public record MentoringForMentorResponse(
        long id,
        long menteeId,
        String menteeUsername,
        String menteeProfileImgUrl,
        String concernSummary,
        String concernDetail,
        String mentorPreference,
        List<String> keywords

) {
    public static MentoringForMentorResponse of(long id, Mentee mentee) {
        return new MentoringForMentorResponse(
                id,
                mentee.getId(),
                mentee.getUsername(),
                mentee.getProfileImgUrl(),
                mentee.getConcernSummary(),
                mentee.getConcernDetail(),
                mentee.getMentorPreference(),
                mentee.getKeywords()
        );
    }
}
