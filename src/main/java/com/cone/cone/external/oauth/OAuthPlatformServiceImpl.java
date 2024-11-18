package com.cone.cone.external.oauth;

import com.cone.cone.domain.user.entity.*;
import com.cone.cone.external.oauth.dto.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class OAuthPlatformServiceImpl implements OAuthPlatformService {
    private final KakaoAuthService kakaoAuthService;
    public UserInfo getUserInfo(PlatformType platformType, String code){
        return switch(platformType) {
            case KAKAO -> kakaoAuthService.getUserInfoByCode(code);
        };
    }
}
