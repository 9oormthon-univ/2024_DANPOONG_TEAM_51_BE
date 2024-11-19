package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.global.response.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_CREATE_ROOM;
import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_GET_ROOMS;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApi {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<ResponseTemplate<Room>> createRoom(RoomCreateRequest request) {
        final Room room = roomService.createRoom(request);
        return ResponseEntity.created(URI.create("/rooms" + room.getId())).body(ResponseTemplate.success(SUCCESS_CREATE_ROOM, room));
    }
}
