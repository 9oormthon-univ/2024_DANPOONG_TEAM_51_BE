package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.domain.user.entity.*;
import java.util.*;

public record MenteeMentoringResponse(
        long mentoringId,
        long mentorId,
        String mentorProfileImgUrl,
        String mentorName,
        List<String> keywords
) {
    public static MenteeMentoringResponse of(long mentoringId, Mentor mentor) {
        return new MenteeMentoringResponse(mentoringId, mentor.getId(), mentor.getProfileImgUrl(),
                mentor.getUsername(), mentor.getKeywords());
    }
}
