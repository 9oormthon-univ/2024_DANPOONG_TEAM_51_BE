package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.MentorStatus;

import java.util.List;


public record MentorResponse (
        Long id,
        UserResponse user,
        String resume,
        String introduction,
        List<String> keywords,
        MentorStatus status,
        String rejectReason
){
    public static MentorResponse from(Mentor mentor) {
        return new MentorResponse(
                mentor.getId(),
                UserResponse.from(mentor.getUser()),
                mentor.getResume(),
                mentor.getIntroduction(),
                mentor.getKeywords(),
                mentor.getStatus(),
                mentor.getRejectReason()
        );
    }
}
