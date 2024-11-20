package com.cone.cone.domain.mentorings.controller;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.global.response.*;
import jakarta.validation.*;
import org.springframework.http.*;

public interface MentoringApi {
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(Long menteeId, @Valid MentoringRequest request);
}
