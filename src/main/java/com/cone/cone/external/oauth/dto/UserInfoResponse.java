package com.cone.cone.external.oauth.dto;

import jakarta.validation.constraints.NotNull;

public record UserInfoResponse(
        @NotNull String id,
        String nickname,
        String profileUrl
) {
    public static UserInfoResponse of(String id) {
        return new UserInfoResponse(id, null, null);
    }

    public static UserInfoResponse of(String id, String nickname, String profileUrl) {
        return new UserInfoResponse(id, nickname, profileUrl);
    }
}