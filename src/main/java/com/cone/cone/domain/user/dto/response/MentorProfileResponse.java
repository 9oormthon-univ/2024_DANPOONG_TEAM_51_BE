package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;

public record MentorProfileResponse(
        long mentorId,
        AuditStatus auditStatus,
        String profileUrl,
        String name,
        String keyword,
        String resume,
        String introduction
) {
}
