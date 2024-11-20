package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MentorApi {
    ResponseEntity<ResponseTemplate<List<Room>>> getRoomsById(Long id);

    ResponseEntity<ResponseTemplate<MentorProfileResponse>> getMentorProfile(Long mentorId);

    ResponseEntity<ResponseTemplate<List<MentorResponse>>> getMentors();
}