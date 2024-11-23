package com.cone.cone.external.jwt;

import static com.cone.cone.global.constant.AuthConstant.ACCESS_TOKEN_SUBJECT;
import static com.cone.cone.global.constant.AuthConstant.ID_CLAIM;
import static com.cone.cone.global.constant.AuthConstant.ROLE_CLAIM;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {
  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.access.expiration}")
  private Long accessTokenExpirationPeriod;

  @Override
  public String createAccessToken(final String memberId, final List<String> roles) {
    Date now = new Date();

    return JWT.create()
        .withSubject(ACCESS_TOKEN_SUBJECT)
        .withClaim(ID_CLAIM, memberId)
        .withClaim(ROLE_CLAIM, roles)
        .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }
}
