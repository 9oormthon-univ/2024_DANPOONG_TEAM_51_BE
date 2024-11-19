package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.global.annotation.SessionAuth;
import com.cone.cone.global.annotation.SessionId;
import com.cone.cone.global.annotation.SessionRole;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.domain.user.service.*;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_GET_ROOMS;
import static com.cone.cone.domain.user.entity.Role.MENTEE;
import static com.cone.cone.domain.user.entity.Role.MENTOR;
import static com.cone.cone.domain.user.code.MentorSuccessCode.SUCCESS_GET_MENTOR_PROFILE;

@RestController
@RequestMapping("/mentors")
@RequiredArgsConstructor
public class MentorController implements MentorApi{
    private final RoomService roomService;
    private final MentorService mentorService;

    @SessionAuth
    @SessionRole(roles = MENTOR)
    @GetMapping("/{id}/rooms")
    public ResponseEntity<ResponseTemplate<List<Room>>> getRoomsById(@SessionId Long id) {
        final List<Room> rooms = roomService.getRoomsByMentorId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_ROOMS, rooms));
    }

    @GetMapping("/{mentorId}")
    public ResponseEntity<ResponseTemplate<MentorProfileResponse>> getMentorProfile(@PathVariable("mentorId") Long mentorId) {
        val response = mentorService.getMentorProfile(mentorId);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MENTOR_PROFILE, response));
    }
}