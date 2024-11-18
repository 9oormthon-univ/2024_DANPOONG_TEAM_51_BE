package com.cone.cone.domain.user.repository;

import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
}