package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.messages.dto.MessageResponse;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomApi {
    ResponseEntity<ResponseTemplate<List<MessageResponse>>> getMessagesById(Long id);
}
