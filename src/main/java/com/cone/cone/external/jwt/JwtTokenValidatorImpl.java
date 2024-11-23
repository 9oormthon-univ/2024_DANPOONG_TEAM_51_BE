package com.cone.cone.external.jwt;

import static com.cone.cone.domain.auth.code.AuthExceptionCode.INVALID_TOKEN;
import static com.cone.cone.domain.auth.code.AuthExceptionCode.UNAUTHORIZED_TOKEN;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.*;
import com.cone.cone.global.exception.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class JwtTokenValidatorImpl implements JwtTokenValidator {

  @Value("${jwt.secretKey}")
  private String secretKey;

  private JWTVerifier getVerifier() {
    return JWT.require(Algorithm.HMAC512(secretKey)).acceptExpiresAt(0).build();
  }

  @Override
  public void validateToken(final String token) {
    try {
      getVerifier().verify(token);
    } catch (TokenExpiredException e) {
      throw new CustomException(UNAUTHORIZED_TOKEN);
    } catch (SignatureVerificationException | JWTDecodeException e) {
      throw new CustomException(INVALID_TOKEN);
    }
  }

  @Override
  public Claim getClaim(final String token, final String claim) {
    return getVerifier().verify(token).getClaim(claim);
  }
}
