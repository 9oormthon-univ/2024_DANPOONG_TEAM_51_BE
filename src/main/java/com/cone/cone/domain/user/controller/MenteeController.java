package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.mentorings.dto.response.MentoringForMenteeResponse;
import com.cone.cone.domain.mentorings.service.MentoringService;
import com.cone.cone.domain.room.dto.RoomForMenteeResponse;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.domain.user.dto.response.MenteeResponse;
import com.cone.cone.domain.user.service.MenteeService;
import com.cone.cone.global.annotation.SessionAuth;
import com.cone.cone.global.annotation.SessionId;
import com.cone.cone.global.annotation.SessionRole;
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
import static com.cone.cone.domain.user.code.MenteeSuccessCode.SUCCESS_GET_MENTEE_PROFILE;
import static com.cone.cone.domain.user.code.MenteeSuccessCode.SUCCESS_GET_MENTORINGS_FOR_MENTEE;
import static com.cone.cone.domain.user.entity.Role.MENTEE;

@RestController
@RequestMapping("/mentees")
@RequiredArgsConstructor
public class MenteeController implements MenteeApi {
    private final RoomService roomService;
    private final MenteeService menteeService;
    private final MentoringService mentoringService;

    @SessionAuth
    @SessionRole(roles = MENTEE)
    @GetMapping("/me/rooms")
    public ResponseEntity<ResponseTemplate<List<RoomForMenteeResponse>>> getRoomsById(@SessionId Long id) {
        final List<RoomForMenteeResponse> rooms = roomService.getRoomsByMenteeId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_ROOMS, rooms));
    }

    @GetMapping("/{menteeId}")
    public ResponseEntity<ResponseTemplate<MenteeResponse>> getMenteeProfile(@PathVariable("menteeId") Long menteeId) {
        val response = menteeService.getMenteeProfile(menteeId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTEE_PROFILE, response));
    }

    @SessionAuth
    @SessionRole(roles = MENTEE)
    @GetMapping("/me/mentorings")
    @Override
    public ResponseEntity<ResponseTemplate<List<MentoringForMenteeResponse>>> getMentoringsForMentee(@SessionId Long menteeId) {
        val response = mentoringService.getMentoringsByMenteeId(menteeId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTORINGS_FOR_MENTEE, response));
    }
}