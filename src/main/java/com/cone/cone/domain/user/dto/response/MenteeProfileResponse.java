package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;

public record MenteeProfileResponse(
        long menteeId,
        String profileImgUrl,
        String name,
        List<String> keyword,
        String concernSummary,
        String concernDetail,
        String mentorPreference
) {
    public static MenteeProfileResponse from(Mentee mentee) {
        return new MenteeProfileResponse(mentee.getId(), mentee.getProfileImgUrl(), mentee.getUsername(),
                mentee.getKeywords(), mentee.getConcernSummary(), mentee.getConcernDetail(), mentee.getMentorPreference());
    }
}
