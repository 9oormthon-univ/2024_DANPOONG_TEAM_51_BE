package com.cone.cone.domain.mentorings.controller;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.global.response.*;
import org.springframework.http.*;

public class MentoringController implements MentoringApi {
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(MentoringRequest request) {
        return null;
    }
}
