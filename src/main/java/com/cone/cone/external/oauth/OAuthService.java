package com.cone.cone.external.oauth;

import com.cone.cone.external.oauth.dto.*;

public interface OAuthService {
    UserInfo getUserInfoByCode(String code);
}
