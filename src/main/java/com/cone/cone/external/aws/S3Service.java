package com.cone.cone.external.aws;

import static com.cone.cone.global.code.CommonExceptionCode.EXTERNAL_SERVER_ERROR;
import static com.cone.cone.global.code.CommonExceptionCode.INVALID_FILE_NAME;
import static com.cone.cone.global.constant.AWSConstant.PRE_SIGNED_URL_EXPIRE_MINUTE;
import static com.cone.cone.global.constant.AWSConstant.VOICE_FILE_FORMAT;

import com.cone.cone.external.aws.vo.*;
import com.cone.cone.global.config.*;
import com.cone.cone.global.exception.*;
import java.net.*;
import java.time.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.*;
import software.amazon.awssdk.services.s3.presigner.model.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Service {

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

    public void deleteFile(final String prefix, final String fileName) {
        String key = prefix + fileName;
        final S3Client s3Client = awsConfig.getS3Client();
        try {
            s3Client.deleteObject(
                    (DeleteObjectRequest.Builder builder) -> builder.bucket(bucketName).key(key).build());
        } catch (S3Exception e) {
            log.error(e.getMessage());
            throw new CustomException(EXTERNAL_SERVER_ERROR);
        }
    }

    public String validateURL(final String prefix, final String fileName) {
        try {
            String zipUrl = prefix + fileName;
            S3Client s3Client = awsConfig.getS3Client();

            HeadObjectRequest request =
                    HeadObjectRequest.builder().bucket(bucketName).key(zipUrl).build();
            s3Client.headObject(request);
            return fileName;
        } catch (S3Exception e) {
            throw new CustomException(INVALID_FILE_NAME);
        }
    }
}
