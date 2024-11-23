package com.cone.cone.external.jwt.dto;

public record IssueTokenResponse(String accessToken) {
    public static IssueTokenResponse of(String accessToken) {
        return new IssueTokenResponse(accessToken);
    }
}
