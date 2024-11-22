package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;

public interface MentoringRecordService {
    MentoringRecordUrlResponse getPreSignedUrlForMentoringRecord(Long mentorId, Long mentoringId);

    MentoringIdResponse createMentoringRecordContent(Long mentoringId, Long mentorId, MentoringRecordRequest request);
}
