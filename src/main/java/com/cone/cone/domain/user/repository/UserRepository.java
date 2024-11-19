package com.cone.cone.domain.user.repository;

import static com.cone.cone.global.code.CommonExceptionCode.AUTHENTICATION_REQUIRED;

import com.cone.cone.domain.user.entity.*;
import com.cone.cone.global.exception.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPlatformId(final String platformId);

    default User findByIdOrThrow(final Long userId) {
        return findById(userId).orElseThrow(() -> new CustomException(AUTHENTICATION_REQUIRED));
    }
}
