package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.MentoringRecordRequest;
import com.cone.cone.domain.mentorings.dto.response.MentoringIdResponse;
import com.cone.cone.domain.mentorings.dto.response.MentoringRecordUrlResponse;
import com.cone.cone.domain.mentorings.entity.Mentoring;
import com.cone.cone.domain.mentorings.repository.MentoringRepository;
import com.cone.cone.external.aws.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.cone.cone.global.constant.AWSConstant.FILE_SPLIT;
import static com.cone.cone.global.constant.AWSConstant.MENTORING_ORIGIN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentoringServiceRecordServiceImpl implements MentoringRecordService{
    private final MentoringRepository mentoringRepository;
    private final S3Service s3Service;

    public MentoringRecordUrlResponse getPreSignedUrlForMentoringRecord(final Long mentoringId, final Long mentorId) {
        val mentoring = mentoringRepository.findMentoringByIdAndMentorIdOrThrow(mentoringId, mentoringId);
        val fileName = getMentoringRecordFileName(mentoring);
        val preSignedUrlVO = s3Service.getUploadPreSignedUrl(MENTORING_ORIGIN, fileName);
        return MentoringRecordUrlResponse.from(preSignedUrlVO);
    }

    private String getMentoringRecordFileName(final Mentoring mentoring) {
        final LocalDateTime now = LocalDateTime.now();
        val room = mentoring.getRoom();
        val mentoringId = mentoring.getId();
        val menteeId = room.getMentee().getId();
        val mentorId = room.getMentor().getId();
        return mentoringId + FILE_SPLIT + mentorId + FILE_SPLIT + menteeId + FILE_SPLIT + now;
    }

    public MentoringIdResponse createMentoringRecordContent(
            final Long mentoringId,
            final Long mentorId,
            final MentoringRecordRequest request
    ) {
        val mentoring = mentoringRepository.findMentoringByIdAndMentorIdOrThrow(mentoringId, mentoringId);
        val savedFileName = s3Service.validateURL(MENTORING_ORIGIN, request.fileName());
        mentoring.updateOriginalRecordFileName(savedFileName);
        return MentoringIdResponse.of(mentoring.getId());
    }
}
