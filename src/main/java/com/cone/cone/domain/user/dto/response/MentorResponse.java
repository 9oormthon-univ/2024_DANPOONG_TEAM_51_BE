package com.cone.cone.domain.user.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;

public record MentorResponse (
        long mentorId,
        MentorStatus mentorStatus,
        String profileImgUrl,
        String name,
        List<String> keywords
){
    public static MentorResponse from(Mentor mentor) {
        return new MentorResponse(mentor.getId(), mentor.getMentorStatus(),
                mentor.getProfileImgUrl(), mentor.getUsername(), mentor.getKeywords());
    }
}
