package com.cone.cone.domain.user.entity;

import static com.cone.cone.global.code.CommonExceptionCode.INVALID_INPUT_VALUE;

import com.cone.cone.global.exception.*;

public enum Role {
    // GUEST: 멘토, 멘티 둘 중 역할을 선택하지 않고 소셜 로그인 후 이탈한 회원이며, GUEST로 권한 변경 요청은 불가합니다
    GUEST, MENTEE, MENTOR;

    public static void validateRole(Role role) {
        boolean isNotValidRole = !(role == MENTEE || role == MENTOR);

        if(isNotValidRole) {
            throw new CustomException(INVALID_INPUT_VALUE);
        }
    }
}
