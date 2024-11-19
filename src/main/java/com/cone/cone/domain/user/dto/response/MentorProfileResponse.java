package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;

public record MentorProfileResponse(
        long mentorId,
        AuditStatus auditStatus,
        String profileImgUrl,
        String name,
        String keyword,
        String resume,
        String introduction
) {
    public static MentorProfileResponse from(Mentor mentor) {
        return new MentorProfileResponse(mentor.getId(), mentor.getAuditStatus(), mentor.getProfileImgUrl(),
                mentor.getUsername(), mentor.getKeyword(), mentor.getResume(), mentor.getIntroduction());
    }
}
