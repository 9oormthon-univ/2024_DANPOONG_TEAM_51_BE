package com.cone.cone.domain.room.service;

import com.cone.cone.domain.room.dto.RoomResponse;
import com.cone.cone.domain.room.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Long mentorId, Long menteeId);
    List<RoomResponse> getRoomsByMentorId(Long mentorId);
    List<RoomResponse> getRoomsByMenteeId(Long menteeId);
}
