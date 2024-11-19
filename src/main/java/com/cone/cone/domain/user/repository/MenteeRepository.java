package com.cone.cone.domain.user.repository;

import static com.cone.cone.domain.user.code.MenteeExceptionCode.NOT_FOUND_MENTEE;

import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.global.exception.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    default Mentee findByIdOrThrow(final Long menteeId) {
        return findById(menteeId).orElseThrow(() -> new CustomException(NOT_FOUND_MENTEE));
    }
}
