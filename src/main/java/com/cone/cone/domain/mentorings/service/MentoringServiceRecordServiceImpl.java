package com.cone.cone.domain.mentorings.service;

import static com.cone.cone.global.constant.AWSConstant.FILE_SPLIT;
import static com.cone.cone.global.constant.AWSConstant.MENTORING_ORIGIN;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.entity.*;
import com.cone.cone.domain.mentorings.repository.*;
import com.cone.cone.external.aws.*;
import java.time.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

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

    private String getMentoringRecordFileName(Mentoring mentoring) {
        LocalDateTime now = LocalDateTime.now();
        val room = mentoring.getRoom();
        val mentoringId = mentoring.getId();
        val menteeId = room.getMentee().getId();
        val mentorId = room.getMentor().getId();
        return mentoringId + FILE_SPLIT + mentorId + FILE_SPLIT + menteeId + FILE_SPLIT + now;
    }

    public MentoringIdResponse createMentoringRecordContent(final Long mentoringId, final Long mentorId,
                                                            final MentoringRecordRequest request) {
        val mentoring = mentoringRepository.findMentoringByIdAndMentorIdOrThrow(mentoringId, mentoringId);
        val savedFileName = s3Service.validateURL(MENTORING_ORIGIN, request.fileName());
        mentoring.updateOriginalRecordFileName(savedFileName);
        return MentoringIdResponse.of(mentoring.getId());
    }
}
