package com.cone.cone.external.jwt;

import com.auth0.jwt.interfaces.*;

public interface JwtTokenValidator {
  void validateToken(String token);

  Claim getClaim(String token, String claim);
}
