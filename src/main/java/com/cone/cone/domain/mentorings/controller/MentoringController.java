package com.cone.cone.domain.mentorings.controller;

import com.cone.cone.domain.mentorings.dto.request.MentoringBookingRequest;
import com.cone.cone.domain.mentorings.dto.request.MentoringRecordRequest;
import com.cone.cone.domain.mentorings.dto.request.MentoringRejectRequest;
import com.cone.cone.domain.mentorings.dto.request.MentoringRequest;
import com.cone.cone.domain.mentorings.dto.response.MentoringIdResponse;
import com.cone.cone.domain.mentorings.dto.response.MentoringRecordUrlResponse;
import com.cone.cone.domain.mentorings.dto.response.MentoringTimeResponse;
import com.cone.cone.domain.mentorings.service.MentoringRecordService;
import com.cone.cone.domain.mentorings.service.MentoringService;
import com.cone.cone.global.annotation.SessionAuth;
import com.cone.cone.global.annotation.SessionId;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cone.cone.domain.mentorings.code.MentoringSuccessCode.*;

@RestController
@RequestMapping("/mentorings")
@RequiredArgsConstructor
public class MentoringController implements MentoringApi {
    private final MentoringService mentoringService;
    private final MentoringRecordService mentoringRecordService;

    @SessionAuth
    @PostMapping
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(
            final @SessionId Long menteeId,
            final @Valid @RequestBody MentoringRequest request
    ) {
        val response = mentoringService.requestMentoring(menteeId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_REQUEST_MENTORING, response));
    }

    @SessionAuth
    @PutMapping("/{mentoringId}/time")
    @Override
    public ResponseEntity<ResponseTemplate<MentoringTimeResponse>> bookingMentoring(
            final @SessionId Long userId,
            final @PathVariable Long mentoringId,
            final @Valid  @RequestBody MentoringBookingRequest request
    ) {
        val response = mentoringService.bookingMentoring(userId, mentoringId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_UPDATE_MENTORING_TIME, response));
    }

    @SessionAuth
    @PostMapping("/{mentoringId}/approve")
    @Override
    public ResponseEntity<ResponseTemplate<Void>> approveMentoring(
            final @SessionId Long mentorId,
            final @PathVariable Long mentoringId
    ) {
        mentoringService.approveMentoring(mentorId, mentoringId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_APPROVE_MENTORING, null));
    }

    @SessionAuth
    @PostMapping("/{mentoringId}/reject")
    @Override
    public ResponseEntity<ResponseTemplate<Void>> rejectMentoring(
            final @SessionId Long mentorId,
            final @PathVariable Long mentoringId,
            final @Valid @RequestBody MentoringRejectRequest request
    ) {
        mentoringService.rejectMentoring(mentorId, mentoringId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_REJECT_MENTORING, null));
    }

    @SessionAuth
    @GetMapping("/{mentoringId}/record")
    @Override
    public ResponseEntity<ResponseTemplate<MentoringRecordUrlResponse>> getPreSignedUrlForMentoringRecord(
            final @PathVariable Long mentoringId,
            final @SessionId Long mentorId
    ) {
        val response = mentoringRecordService.getPreSignedUrlForMentoringRecord(mentoringId, mentorId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_PRE_SIGNED_URL_FOR_MENTORING_RECORD, response));
    }

    @SessionAuth
    @PostMapping("/{mentoringId}/record")
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> createMentoringRecordContent(
            final @PathVariable Long mentoringId,
            final @SessionId Long mentorId,
            final @Valid  @RequestBody MentoringRecordRequest request
    ) {
        val response = mentoringRecordService.createMentoringRecordContent(mentoringId, mentorId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_CREATE_MENTORING_CONTENT, response));
    }
}
