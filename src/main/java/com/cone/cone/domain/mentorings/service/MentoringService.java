package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import java.util.*;

public interface MentoringService {
    MentoringIdResponse requestMentoring(Long menteeId, MentoringRequest request);

    List<MentoringForMenteeResponse> getMentoringsByMenteeId(Long menteeId);

    List<MentoringForMentorResponse> getMentoringsByMentorId(Long mentorId);

    MentoringTimeResponse bookingMentoring(Long userId, Long mentoringId, MentoringBookingRequest request);

    void approveMentoring(Long mentorId, Long mentoringId);

    void rejectMentoring(Long mentorId, Long mentoringId, MentoringRejectRequest request);
}
