package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;

public record MentorProfileResponse(
        Long mentorId,
        MentorStatus mentorStatus,
        String profileImgUrl,
        String name,
        List<String> keywords,
        String resume,
        String introduction
) {
    public static MentorProfileResponse from(Mentor mentor) {
        return new MentorProfileResponse(mentor.getId(), mentor.getStatus(), mentor.getProfileImgUrl(),
                mentor.getName(), mentor.getKeywords(), mentor.getResume(), mentor.getIntroduction());
    }
}
