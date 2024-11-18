package com.cone.cone.external.oauth.dto;

import com.fasterxml.jackson.annotation.*;

public record AccessTokenResponse(
        @JsonProperty("access_token") String accessToken
) {}
