package com.cone.cone.domain.mentorings.repository;

import com.cone.cone.domain.mentorings.entity.*;
import java.util.*;

import com.cone.cone.global.exception.CustomException;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import static com.cone.cone.domain.mentorings.code.MentoringExceptionCode.INVALID_REQUEST_FIND_MENTORING;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {

    default Mentoring findByIdOrThrow(final Long id) {
        return findById(id).orElseThrow(() -> new CustomException(INVALID_REQUEST_FIND_MENTORING));
    }

    @Query("select m from Mentoring m " +
            "join fetch m.room r " +
            "join fetch r.mentee " +
            "where r.mentee.id = :menteeId " +
            "order by m.createdAt desc")
    List<Mentoring> findAllByMenteeIdOrderCreatedAtDesc(@Param("menteeId") Long menteeId);

    @Query("select m from Mentoring m " +
            "join fetch m.room r " +
            "join fetch r.mentor " +
            "where r.mentor.id = :mentorId " +
            "order by m.createdAt desc")
    List<Mentoring> findAllByMentorIdOrderCreatedAtDesc(@Param("mentorId") Long menteeId);
}
