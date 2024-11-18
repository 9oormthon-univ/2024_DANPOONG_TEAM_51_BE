package com.cone.cone.domain.user.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.*;

@RestController
public class LoginTestController {

    @Value("${oauth.kakao.clientId}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @GetMapping("/test/kakao")
    public RedirectView kakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code";

        return new RedirectView(kakaoAuthUrl);
    }
}
