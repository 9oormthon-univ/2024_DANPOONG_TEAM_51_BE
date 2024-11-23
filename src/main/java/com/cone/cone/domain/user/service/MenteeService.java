package com.cone.cone.domain.user.service;

import com.cone.cone.domain.user.dto.response.*;

public interface MenteeService {
    MenteeResponse getMenteeProfile(Long menteeId);
}
