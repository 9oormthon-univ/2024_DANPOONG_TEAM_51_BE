package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.mentorings.dto.response.MentoringForMenteeResponse;
import com.cone.cone.domain.room.dto.RoomForMenteeResponse;
import com.cone.cone.domain.user.dto.response.MenteeResponse;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenteeApi {
    ResponseEntity<ResponseTemplate<List<RoomForMenteeResponse>>> getRoomsById(Long id);

    ResponseEntity<ResponseTemplate<MenteeResponse>> getMenteeProfile(Long menteeId);

    ResponseEntity<ResponseTemplate<List<MentoringForMenteeResponse>>> getMentoringsForMentee(Long menteeId);
}