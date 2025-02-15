package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.mentorings.dto.response.MentoringForMentorResponse;
import com.cone.cone.domain.room.dto.RoomForMentorResponse;
import com.cone.cone.domain.user.dto.response.MentorResponse;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MentorApi {
    ResponseEntity<ResponseTemplate<List<RoomForMentorResponse>>> getRoomsById(Long id);

    ResponseEntity<ResponseTemplate<MentorResponse>> getMentorProfile(Long mentorId);

    ResponseEntity<ResponseTemplate<List<MentorResponse>>> getMentors();

    ResponseEntity<ResponseTemplate<List<MentoringForMentorResponse>>> getMentorings(Long id);
}