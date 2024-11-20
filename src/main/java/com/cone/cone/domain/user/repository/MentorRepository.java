package com.cone.cone.domain.user.repository;

import static com.cone.cone.domain.user.code.MentorExceptionCode.NOT_FOUND_MENTOR;
import static com.cone.cone.global.code.CommonExceptionCode.AUTHENTICATION_REQUIRED;

import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.User;
import com.cone.cone.global.exception.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    default Mentor findByIdOrThrow(final Long mentorId) {
        return findById(mentorId).orElseThrow(() -> new CustomException(NOT_FOUND_MENTOR));
    }

    @Query("select m from Mentor m where m.auditStatus = com.cone.cone.domain.user.entity.AuditStatus.APPROVED")
    List<Mentor> findApprovedMentors();
}
