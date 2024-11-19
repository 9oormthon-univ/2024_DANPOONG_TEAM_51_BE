package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService{

    public MentorProfileResponse getMentorProfile(final Long mentorId) {
        return null;
    }
}
