package com.cone.cone.domain.mentorings.dto.response;

import com.cone.cone.external.aws.vo.*;

public record MentoringRecordUrlResponse(
        String fileName,
        String preSignedUrl
) {
    public static MentoringRecordUrlResponse from(PreSignedUrlVO vo) {
        return new MentoringRecordUrlResponse(vo.fileName(), vo.preSignedUrl());
    }
}
