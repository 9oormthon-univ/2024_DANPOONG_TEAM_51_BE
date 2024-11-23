package com.cone.cone.domain.user.repository;

import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.MentorStatus;
import com.cone.cone.global.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.cone.cone.domain.user.code.MentorExceptionCode.NOT_FOUND_MENTOR;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    default Mentor findByIdOrThrow(final Long mentorId) {
        return findById(mentorId).orElseThrow(() -> new CustomException(NOT_FOUND_MENTOR));
    }

    List<Mentor> findAllByStatusLike(MentorStatus status);
}
