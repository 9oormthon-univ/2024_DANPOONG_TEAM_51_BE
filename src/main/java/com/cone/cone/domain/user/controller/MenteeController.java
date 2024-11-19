package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.domain.user.dto.response.*;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.*;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_GET_ROOMS;

@RestController
@RequestMapping("/mentees")
@RequiredArgsConstructor
public class MenteeController implements MenteeApi {
    private final RoomService roomService;

    @GetMapping("/{id}/rooms")
    public ResponseEntity<ResponseTemplate<List<Room>>> getRoomsById(@PathVariable Long id) {
        final List<Room> rooms = roomService.getRoomsByMenteeId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_ROOMS, rooms));
    }

    @GetMapping("/{menteeId}")
    public ResponseEntity<ResponseTemplate<MenteeProfileResponse>> getMenteeProfile(@PathVariable("menteeId") Long menteeId) {
        return null;
    }
}