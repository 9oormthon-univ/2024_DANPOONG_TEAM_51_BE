package com.cone.cone.external.jwt;

import java.util.*;

public interface JwtTokenProvider {
  String createAccessToken(Long userId, List<String> roles);
}
