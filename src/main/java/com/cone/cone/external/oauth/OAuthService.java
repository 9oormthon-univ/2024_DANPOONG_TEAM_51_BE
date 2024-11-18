package com.cone.cone.external.oauth;

import com.cone.cone.external.oauth.dto.*;

public interface OAuthService {
    UserInfoResponse getUserInfoByCode(String code);
}
