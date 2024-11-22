package com.cone.cone.domain.mentorings.controller;

import static com.cone.cone.domain.mentorings.code.MentoringSuccessCode.*;
import static com.cone.cone.domain.user.entity.Role.MENTEE;

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

    @SessionAuth
    @SessionRole(roles = MENTEE)
    @PostMapping
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(@SessionId Long menteeId, @RequestBody MentoringRequest request) {
        val response = mentoringService.requestMentoring(menteeId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_REQUEST_MENTORING, response));
    }

    @Override
    public ResponseEntity<ResponseTemplate<MentoringTimeResponse>> bookingMentoring(Long userId, Long mentoringId, MentoringBookingRequest request) {
        val response = mentoringService.bookingMentoring(userId, mentoringId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_BOOKING_MENTORING_TIME, response));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Void>> approveMentoring(Long mentorId, Long mentoringId) {
        mentoringService.approveMentoring(mentorId, mentoringId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_APPROVE_MENTORING, null));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Void>> rejectMentoring(Long mentorId, Long mentoringId, MentoringRejectRequest request) {
        mentoringService.rejectMentoring(mentorId, mentoringId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_REJECT_MENTORING, null));
    }
}
