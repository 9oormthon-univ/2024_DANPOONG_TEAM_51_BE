package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.MentorResponse;
import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.MentorStatus;
import com.cone.cone.domain.user.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService{
    private final MentorRepository mentorRepository;

    public MentorResponse getMentorProfile(final Long mentorId) {
        Mentor mentor = mentorRepository.findByIdOrThrow(mentorId);
        return MentorResponse.from(mentor);
    }

    public List<MentorResponse> getApprovedMentors() {
        List<Mentor> mentors = mentorRepository.findAllByStatusLike(MentorStatus.APPROVED);
        return mentors.stream()
                .map(MentorResponse::from)
                .toList();
    }
}
