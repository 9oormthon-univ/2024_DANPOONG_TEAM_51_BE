package com.cone.cone.external.oauth;

import com.cone.cone.domain.user.entity.*;
import com.cone.cone.external.oauth.dto.*;

public interface OAuthPlatformService {
    UserInfo getUserInfo(PlatformType platformType, String code);
}
