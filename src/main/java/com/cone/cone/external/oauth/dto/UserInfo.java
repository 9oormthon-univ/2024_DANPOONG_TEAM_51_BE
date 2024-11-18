package com.cone.cone.external.oauth.dto;

import jakarta.validation.constraints.NotNull;

public record UserInfo(
        @NotNull String id,
        String nickname,
        String profileUrl
) {
    public static UserInfo of(String id) {
        return new UserInfo(id, null, null);
    }

    public static UserInfo of(String id, String nickname, String profileUrl) {
        return new UserInfo(id, nickname, profileUrl);
    }
}