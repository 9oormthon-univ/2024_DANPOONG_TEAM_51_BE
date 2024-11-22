package com.cone.cone.global.config;

import static com.cone.cone.global.constant.AWSConstant.AWS_ACCESS_KEY_ID;
import static com.cone.cone.global.constant.AWSConstant.AWS_SECRET_ACCESS_KEY;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.presigner.*;

@Configuration
public class AWSConfig {
    @Value("${cloud.aws.credential.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credential.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static")
    private String region;


    @Bean
    public SystemPropertyCredentialsProvider systemPropertyCredentialsProvider() {
        System.setProperty(AWS_ACCESS_KEY_ID, accessKey);
        System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey);
        return SystemPropertyCredentialsProvider.create();
    }

    @Bean
    public Region getRegion() {
        return Region.of(region);
    }

    @Bean
    public S3Client getS3Client() {
        return S3Client.builder()
                .region(getRegion())
                .credentialsProvider(systemPropertyCredentialsProvider())
                .build();
    }

    @Bean
    public S3Presigner getS3Presigner() {
        return S3Presigner.builder()
                .region(getRegion())
                .credentialsProvider(systemPropertyCredentialsProvider())
                .build();
    }
}