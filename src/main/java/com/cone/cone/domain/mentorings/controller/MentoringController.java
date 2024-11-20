package com.cone.cone.domain.mentorings.controller;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.service.*;
import com.cone.cone.global.annotation.*;
import com.cone.cone.global.response.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentorings")
@RequiredArgsConstructor
public class MentoringController implements MentoringApi {
    private final MentoringService mentoringService;

    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(@SessionId Long menteeId, MentoringRequest request) {
        val response = mentoringService.requestMentoring(menteeId, request);
        return null;
    }
}
