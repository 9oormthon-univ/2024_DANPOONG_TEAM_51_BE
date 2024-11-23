package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.messages.entity.Message;
import com.cone.cone.domain.messages.service.MessageService;
import com.cone.cone.global.response.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cone.cone.domain.messages.code.MessageSuccessCode.SUCCESS_GET_MESSAGES;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController implements RoomApi {
    private final MessageService messageService;

    @GetMapping("/{id}/messages")
    public ResponseEntity<ResponseTemplate<List<Message>>> getMessagesById(final @PathVariable Long id) {
        final List<Message> messages = messageService.getMessagesByRoomId(id);
        return ResponseEntity.ok(ResponseTemplate.success(SUCCESS_GET_MESSAGES, messages));
    }
}
