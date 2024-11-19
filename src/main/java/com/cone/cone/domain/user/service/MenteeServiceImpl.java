package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenteeServiceImpl implements MenteeService{
    private MenteeRepository menteeRepository;

    public MenteeProfileResponse getMentorProfile(final Long menteeId) {
        val mentee = menteeRepository.findByIdOrThrow(menteeId);
        return MenteeProfileResponse.from(mentee);
    }
}
