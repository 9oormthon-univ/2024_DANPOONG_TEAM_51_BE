package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.global.response.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface RoomApi {
    ResponseEntity<ResponseTemplate<Room>> createRoom(RoomCreateRequest request);
}
