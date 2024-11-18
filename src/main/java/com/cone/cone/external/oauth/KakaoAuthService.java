package com.cone.cone.external.oauth;

import static com.cone.cone.external.code.ClientExceptionCode.INVALID_AUTH_ACCESS_TOKEN;
import static com.cone.cone.external.code.ClientExceptionCode.INVALID_AUTH_CODE;
import static com.cone.cone.global.code.CommonExceptionCode.INTERNAL_SERVER_ERROR;
import static com.cone.cone.global.constant.OAuthConstant.KAKAO_TOKEN_URL;
import static com.cone.cone.global.constant.OAuthConstant.KAKAO_USER_INFO_URL;

import com.cone.cone.external.oauth.dto.*;
import com.cone.cone.global.exception.*;
import java.util.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Component
@RequiredArgsConstructor
public class KakaoAuthService implements OAuthService{
    private final RestTemplate restTemplate;
    @Value("${oauth.kakao.client-id}")
    private String clientId;
    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    public UserInfoResponse getUserInfoByCode(String code) {
        val accessToken = getAccessToken(code);
        return getUserInfo(accessToken);
    }

    public String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        String body = "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&code=" + code;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                request,
                AccessTokenResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(INVALID_AUTH_CODE);
        }

        return response.getBody().accessToken();
    }

    public UserInfoResponse getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                request,
                Map.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(INVALID_AUTH_ACCESS_TOKEN);
        }

        return getUserInfoByParsing(response.getBody());
    }


    public UserInfoResponse getUserInfoByParsing(Map<Object, Object> responseBody) {
        val id = responseBody.get("id").toString();
        val kakaoAccount = (Map<String, Object>) responseBody.get("kakao_account");
        val profile = (Map<String, Object>) kakaoAccount.get("profile");
        val nickname = profile != null ? (String) profile.get("nickname") : null;
        val profileUrl = profile != null ? (String) profile.get("profile_image") : null;

        return UserInfoResponse.of(id, nickname, profileUrl);
    }
}
