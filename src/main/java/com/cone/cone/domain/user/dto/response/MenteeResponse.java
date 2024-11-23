package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;


public record MenteeResponse(
        Long id,
        String name,
        String profileImgUrl,
        Role role,
        String concernSummary,
        String concernDetail,
        String mentorPreference,
        List<String> keywords
) {
    public static MenteeResponse from(Mentee mentee) {
        return new MenteeResponse(
                mentee.getId(),
                mentee.getName(),
                mentee.getProfileImgUrl(),
                mentee.getUser().getRole(),
                mentee.getConcernSummary(),
                mentee.getConcernDetail(),
                mentee.getMentorPreference(),
                mentee.getKeywords()
        );
    }
}
