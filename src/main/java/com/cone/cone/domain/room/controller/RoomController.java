package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.messages.entity.Message;
import com.cone.cone.domain.messages.service.MessageService;
import com.cone.cone.domain.messages.service.MessageServiceImpl;
import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.cone.cone.domain.messages.code.MessageSuccessCode.SUCCESS_GET_MESSAGES;
import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_CREATE_ROOM;
import static com.cone.cone.domain.room.code.RoomSuccessCode.SUCCESS_GET_ROOMS;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApi {
    private final RoomService roomService;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<ResponseTemplate<Room>> createRoom(@RequestBody @Valid RoomCreateRequest request) {
        final Room room = roomService.createRoom(request);
        return ResponseEntity.created(URI.create("/rooms" + room.getId())).body(ResponseTemplate.success(SUCCESS_CREATE_ROOM, room));
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<ResponseTemplate<List<Message>>> getMessagesById(@PathVariable Long id) {
        final List<Message> messages = messageService.getMessagesByRoomId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MESSAGES, messages));
    }
}
