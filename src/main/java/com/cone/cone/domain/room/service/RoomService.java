package com.cone.cone.domain.room.service;

import com.cone.cone.domain.room.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Long mentorId, Long menteeId);
    List<Room> getRoomsByMentorId(Long mentorId);
    List<Room> getRoomsByMenteeId(Long menteeId);
}
