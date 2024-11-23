package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.MentorStatus;
import com.cone.cone.domain.user.entity.Role;

import java.util.List;


public record MentorResponse (
        Long id,
        String name,
        String profileImgUrl,
        Role role,
        String resume,
        String introduction,
        List<String> keywords,
        MentorStatus status,
        String rejectReason
){
    public static MentorResponse from(Mentor mentor) {
        return new MentorResponse(
                mentor.getId(),
                mentor.getName(),
                mentor.getProfileImgUrl(),
                mentor.getUser().getRole(),
                mentor.getResume(),
                mentor.getIntroduction(),
                mentor.getKeywords(),
                mentor.getStatus(),
                mentor.getRejectReason()
        );
    }
}
