package com.cone.cone.domain.mentorings.repository;

import com.cone.cone.domain.mentorings.entity.Mentoring;
import com.cone.cone.global.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static com.cone.cone.domain.mentorings.code.MentoringExceptionCode.NOT_FOUND_MENTORING;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {

    default Mentoring findByIdOrThrow(final Long id) {
        return findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_MENTORING));
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

    @Query("select m from Mentoring m " +
            "join fetch m.room r " +
            "join fetch r.mentor " +
            "where  m.id = :mentoringId and r.mentor.id = :mentorId")
    Optional<Mentoring> findMentoringByIdAndMentorId(@Param("mentoringId") Long mentoringId, @Param("mentorId") Long mentorId);

    default Mentoring findMentoringByIdAndMentorIdOrThrow(Long mentoringId, Long mentorId){
        return findMentoringByIdAndMentorId(mentoringId, mentorId).orElseThrow(
                () -> new CustomException(NOT_FOUND_MENTORING)
        );
    }
}
