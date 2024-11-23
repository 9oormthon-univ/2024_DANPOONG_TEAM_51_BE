package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.mentorings.dto.response.MentoringForMentorResponse;
import com.cone.cone.domain.mentorings.service.MentoringService;
import com.cone.cone.domain.room.dto.RoomResponse;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.domain.user.dto.response.MentorResponse;
import com.cone.cone.domain.user.service.MentorService;
import com.cone.cone.global.annotation.SessionAuth;
import com.cone.cone.global.annotation.SessionId;
import com.cone.cone.global.response.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_GET_ROOMS;
import static com.cone.cone.domain.user.code.MentorSuccessCode.*;

@RestController
@RequestMapping("/mentors")
@RequiredArgsConstructor
public class MentorController implements MentorApi{
    private final RoomService roomService;
    private final MentorService mentorService;
    private final MentoringService mentoringService;

    @SessionAuth
    @GetMapping("/me/rooms")
    public ResponseEntity<ResponseTemplate<List<RoomResponse>>> getRoomsById(final @SessionId Long id) {
        final List<RoomResponse> rooms = roomService.getRoomsByMentorId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_ROOMS, rooms));
    }

    @SessionAuth
    @GetMapping("/{mentorId}")
    public ResponseEntity<ResponseTemplate<MentorResponse>> getMentorProfile(final @PathVariable("mentorId") Long mentorId) {
        val response = mentorService.getMentorProfile(mentorId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTOR_PROFILE, response));
    }

    @SessionAuth
    @GetMapping
    public ResponseEntity<ResponseTemplate<List<MentorResponse>>> getMentors() {
        val response = mentorService.getMentors();
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTORS, response));
    }

    @SessionAuth
    @GetMapping("/me/mentorings")
    public ResponseEntity<ResponseTemplate<List<MentoringForMentorResponse>>> getMentorings(final @SessionId Long id) {
        val response = mentoringService.getMentoringsByMentorId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTORINGS_FOR_MENTOR, response));
    }
}