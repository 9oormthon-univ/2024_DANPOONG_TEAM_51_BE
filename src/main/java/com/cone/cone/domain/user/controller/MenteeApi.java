package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.room.dto.RoomResponse;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenteeApi {
    ResponseEntity<ResponseTemplate<List<RoomResponse>>> getRoomsById(Long id);

    ResponseEntity<ResponseTemplate<MenteeResponse>> getMenteeProfile(Long menteeId);

    ResponseEntity<ResponseTemplate<List<MentoringForMenteeResponse>>> getMentoringsForMentee(Long menteeId);
}