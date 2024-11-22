package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import java.util.*;

public interface MentoringService {
    MentoringIdResponse requestMentoring(Long menteeId, MentoringRequest request);

    List<MenteeMentoringResponse> getMentoringsByMenteeId(Long menteeId);

    List<MentorMentoringResponse> getMentoringsByMentorId(Long mentorId);

    MentoringTimeResponse bookingMentoring(Long userId, MentoringRequest request);

    Void permitMentoring(Long mentorId, MentoringRequest request);

    Void rejectMentoring(Long mentorId, MentoringRequest request);
}
