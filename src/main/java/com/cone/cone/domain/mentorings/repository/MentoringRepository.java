package com.cone.cone.domain.mentorings.repository;

import static com.cone.cone.domain.mentorings.code.MentoringExceptionCode.INVALID_MENTORING_REQUEST;

import com.cone.cone.domain.mentorings.entity.*;
import com.cone.cone.global.exception.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
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

    @Query("select m from Mentoring m " +
            "join fetch m.room r " +
            "join fetch r.mentor " +
            "where  m.id = :mentoringId and r.mentor.id = :mentorId")
    Optional<Mentoring> findMentoringByIdAndMentorId(@Param("mentoringId") Long mentoringId, @Param("mentorId") Long mentorId);

    default Mentoring findMentoringByIdAndMentorIdOrThrow(Long mentoringId, Long mentorId){
        return findMentoringByIdAndMentorId(mentoringId, mentorId).orElseThrow(
                () -> new CustomException(INVALID_MENTORING_REQUEST)
        );
    }
}
