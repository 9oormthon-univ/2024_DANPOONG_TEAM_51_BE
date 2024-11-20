package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;

public interface MentoringService {
    MentoringIdResponse requestMentoring(MentoringRequest request);
}
