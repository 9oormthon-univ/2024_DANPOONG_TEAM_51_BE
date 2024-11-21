package com.cone.cone.domain.mentorings.repository;

import com.cone.cone.domain.mentorings.entity.*;
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
}
