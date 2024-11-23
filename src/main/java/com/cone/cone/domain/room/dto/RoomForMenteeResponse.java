package com.cone.cone.domain.room.dto;

import com.cone.cone.domain.room.entity.Room;

import java.time.LocalDateTime;
import java.util.List;


public record RoomForMenteeResponse(
        Long id,
        String mentorName,
        List<String> mentorKeywords,
        boolean isStable,
        LocalDateTime menteeLastViewedAt
) {
    public static RoomForMenteeResponse from(final Room room) {
        return new RoomForMenteeResponse(
                room.getId(),
                room.getMentor().getUser().getName(),
                room.getMentor().getKeywords(),
                room.isStable(),
                room.getMenteeLastViewedAt()
        );
    }
}