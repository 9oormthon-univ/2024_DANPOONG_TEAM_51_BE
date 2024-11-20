package com.cone.cone.external.oauth;

import com.cone.cone.domain.user.entity.*;
import com.cone.cone.external.oauth.dto.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthPlatformServiceImpl implements OAuthPlatformService {
    private final KakaoAuthService kakaoAuthService;
    public UserInfoResponse getUserInfo(PlatformType platformType, String code){
        return switch(platformType) {
            case KAKAO -> kakaoAuthService.getUserInfoByCode(code);
        };
    }
}
