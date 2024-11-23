package com.cone.cone.external.jwt;

import static com.cone.cone.domain.auth.code.AuthExceptionCode.INVALID_REQUEST_LOGIN;
import static com.cone.cone.domain.auth.code.AuthExceptionCode.INVALID_TOKEN_HEADER;
import static com.cone.cone.global.constant.AuthConstant.BEARER;
import static com.cone.cone.global.constant.AuthConstant.ID_CLAIM;
import static com.cone.cone.global.constant.AuthConstant.ROLE_CLAIM;

import com.cone.cone.domain.user.entity.*;
import com.cone.cone.external.jwt.dto.*;
import com.cone.cone.global.exception.*;
import jakarta.servlet.http.*;
import java.util.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.access.header}")
    private String accessHeader;

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public IssueTokenResponse issueToken(final Long userId, final List<String> roles) {
        String accessToken = jwtTokenProvider.createAccessToken(userId, roles);

        boolean isServiceUser =
                roles.stream()
                        .anyMatch(role -> role.equals(Role.MENTEE.name()) || role.equals(Role.MENTOR.name()));

        if (isServiceUser) {
            return IssueTokenResponse.of(accessToken);
        }

        throw new CustomException(INVALID_REQUEST_LOGIN);
    }

    @Override
    public String getMemberIdFromAccessToken(final String accessToken) {
        return jwtTokenValidator.getClaim(accessToken, ID_CLAIM).asString();
    }

    @Override
    public List<String> getRolesFromAccessToken(final String accessToken) {
        return jwtTokenValidator.getClaim(accessToken, ROLE_CLAIM).asList(String.class);
    }

    @Override
    public String extractAccessToken(final HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""))
                .orElseThrow(() -> new CustomException(INVALID_TOKEN_HEADER));
    }
}
