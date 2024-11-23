package com.cone.cone.external.jwt;

import java.util.*;

public interface JwtTokenProvider {
  String createAccessToken(String memberId, List<String> roles);
}
