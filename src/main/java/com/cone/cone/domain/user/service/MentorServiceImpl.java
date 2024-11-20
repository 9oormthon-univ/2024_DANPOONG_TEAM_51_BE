package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService{
    private final MentorRepository mentorRepository;

    public MentorProfileResponse getMentorProfile(final Long mentorId) {
        Mentor mentor = mentorRepository.findByIdOrThrow(mentorId);
        return MentorProfileResponse.from(mentor);
    }

    public List<MentorResponse> getMentors() {
        List<Mentor> mentors = mentorRepository.findApprovedMentors();
        return mentors.stream()
                .map(MentorResponse::from)
                .toList();
    }
}
