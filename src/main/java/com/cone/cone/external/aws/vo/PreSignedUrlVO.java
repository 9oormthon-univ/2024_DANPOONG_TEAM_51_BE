package com.cone.cone.external.aws.vo;

public record PreSignedUrlVO(
        String fileName,
        String preSignedUrl
) {
    public static PreSignedUrlVO of(String fileName, String preSignedUrl) {
        return new PreSignedUrlVO(fileName, preSignedUrl);
    }
}
