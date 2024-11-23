package com.cone.cone.domain.room.service;

import com.cone.cone.domain.room.dto.RoomForMenteeResponse;
import com.cone.cone.domain.room.dto.RoomForMentorResponse;
import com.cone.cone.domain.room.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Long mentorId, Long menteeId);
    List<RoomForMentorResponse> getRoomsByMentorId(Long mentorId);
    List<RoomForMenteeResponse> getRoomsByMenteeId(Long menteeId);
}
