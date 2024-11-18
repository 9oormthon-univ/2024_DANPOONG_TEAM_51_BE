package com.cone.cone.external.oauth;

import com.cone.cone.external.oauth.dto.*;

public interface OAuthPlatformService {
    UserInfo getUserInfo(String code);
}
