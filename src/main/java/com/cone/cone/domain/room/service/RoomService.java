package com.cone.cone.domain.room.service;

import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(RoomCreateRequest request);
    List<Room> getRoomsByMentorId(Long mentorId);
    List<Room> getRoomsByMenteeId(Long menteeId);
}
