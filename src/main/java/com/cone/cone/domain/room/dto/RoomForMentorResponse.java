package com.cone.cone.domain.room.dto;

import com.cone.cone.domain.room.entity.Room;

import java.time.LocalDateTime;
import java.util.List;


public record RoomForMentorResponse(
        Long id,
        String menteeName,
        List<String> menteeKeywords,
        boolean isStable,
        LocalDateTime mentorLastViewedAt
) {
    public static RoomForMentorResponse from(final Room room) {
        return new RoomForMentorResponse(
                room.getId(),
                room.getMentee().getUser().getName(),
                room.getMentee().getKeywords(),
                room.isStable(),
                room.getMentorLastViewedAt()
        );
    }
}