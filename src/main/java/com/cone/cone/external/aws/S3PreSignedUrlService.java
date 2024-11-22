package com.cone.cone.external.aws;

import static com.cone.cone.global.constant.AWSConstant.PRE_SIGNED_URL_EXPIRE_MINUTE;
import static com.cone.cone.global.constant.AWSConstant.VOICE_FILE_FORMAT;

import com.cone.cone.external.aws.vo.*;
import com.cone.cone.global.config.*;
import java.net.*;
import java.time.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.*;
import software.amazon.awssdk.services.s3.presigner.model.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3PreSignedUrlService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    private final AWSConfig awsConfig;

    public PreSignedUrlVO getUploadPreSignedUrl(final String prefix, final String fileName) {
        val generatedFileName = generateVoiceFile(fileName);
        val key = prefix + generatedFileName;

        S3Presigner preSigner = awsConfig.getS3Presigner();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        // S3에서 업로드는 PUT 요청
        PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                .putObjectRequest(putObjectRequest)
                .build();

        URL url = preSigner.presignPutObject(preSignedUrlRequest).url();

        return PreSignedUrlVO.of(generatedFileName, url.toString());
    }

    private String generateVoiceFile(String fileName) {
        return fileName + VOICE_FILE_FORMAT;
    }
}
