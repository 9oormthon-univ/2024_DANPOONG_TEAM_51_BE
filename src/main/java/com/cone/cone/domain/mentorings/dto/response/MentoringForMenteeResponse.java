package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;

public record MentoringForMenteeResponse(
        Long mentoringId,
        Long mentorId,
        String mentorProfileImgUrl,
        String mentorName,
        List<String> keywords
) {
    public static MentoringForMenteeResponse of(Long mentoringId, Mentor mentor) {
        return new MentoringForMenteeResponse(mentoringId, mentor.getId(), mentor.getProfileImgUrl(),
                mentor.getName(), mentor.getKeywords());
    }
}
