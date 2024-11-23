package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.*;
import java.util.*;

public interface MentorService {
    MentorResponse getMentorProfile(Long mentorId);
    List<MentorResponse> getApprovedMentors();
}
