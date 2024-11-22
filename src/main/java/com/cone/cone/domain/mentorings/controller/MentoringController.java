package com.cone.cone.domain.mentorings.controller;

import static com.cone.cone.domain.mentorings.code.MentoringSuccessCode.SUCCESS_CREATE_MENTORING_CONTENT;
import static com.cone.cone.domain.mentorings.code.MentoringSuccessCode.SUCCESS_GET_PRESIGNED_URL_FOR_MENTORING_RECORD;
import static com.cone.cone.domain.mentorings.code.MentoringSuccessCode.SUCCESS_REQUEST_MENTORING;
import static com.cone.cone.domain.user.entity.Role.MENTEE;
import static com.cone.cone.domain.user.entity.Role.MENTOR;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.service.*;
import com.cone.cone.global.annotation.*;
import com.cone.cone.global.response.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentorings")
@RequiredArgsConstructor
public class MentoringController implements MentoringApi {
    private final MentoringService mentoringService;
    private final MentoringRecordService mentoringRecordService;

    @SessionAuth
    @SessionRole(roles = MENTEE)
    @PostMapping
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> requestMentoring(@SessionId Long menteeId, @RequestBody MentoringRequest request) {
        val response = mentoringService.requestMentoring(menteeId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_REQUEST_MENTORING, response));
    }

    @SessionAuth
    @SessionRole(roles = MENTOR)
    @GetMapping("/{mentoringId}/record")
    @Override
    public ResponseEntity<ResponseTemplate<MentoringRecordUrlResponse>> getPreSignedUrlForMentoringRecord(
            @PathVariable("mentoringId") Long mentoringId, @SessionId Long mentorId) {
        val response = mentoringRecordService.getPreSignedUrlForMentoringRecord(mentoringId, mentorId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_PRESIGNED_URL_FOR_MENTORING_RECORD, response));
    }

    @SessionAuth
    @SessionRole(roles = MENTOR)
    @PostMapping("/{mentoringId}/record")
    @Override
    public ResponseEntity<ResponseTemplate<MentoringIdResponse>> createMentoringRecordContent(
            @PathVariable("mentoringId") Long mentoringId, @SessionId Long mentorId,
            @Valid @RequestBody MentoringRecordRequest request) {
        val response = mentoringRecordService.createMentoringRecordContent(mentoringId, mentorId, request);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_CREATE_MENTORING_CONTENT, response));
    }
}
