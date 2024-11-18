package com.cone.cone.domain.room.controller;

import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomApi {
    ResponseEntity<ResponseTemplate<Room>> createRoom(@Valid RoomCreateRequest request);
    ResponseEntity<ResponseTemplate<List<Room>>> getRoomsByMentorId(@NotNull Long mentorId);
    ResponseEntity<ResponseTemplate<List<Room>>> getRoomsByMenteeId(@NotNull Long menteeId);
}
