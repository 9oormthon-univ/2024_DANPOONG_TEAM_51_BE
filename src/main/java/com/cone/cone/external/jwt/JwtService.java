package com.cone.cone.external.jwt;

import com.cone.cone.external.jwt.dto.*;
import jakarta.servlet.http.*;
import java.util.*;

public interface JwtService {
    IssueTokenResponse issueToken(String memberId, List<String> roles);

    String extractAccessToken(HttpServletRequest request);

    String getMemberIdFromAccessToken(String accessToken);

    List<String> getRolesFromAccessToken(String accessToken);
}

