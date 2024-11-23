package com.cone.cone.domain.room.dto;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.dto.response.MenteeResponse;
import com.cone.cone.domain.user.dto.response.MentorResponse;

import java.time.LocalDateTime;


public record RoomResponse (
        Long id,
        MentorResponse mentor,
        MenteeResponse mentee,
        boolean isStable,
        LocalDateTime mentorLastViewedAt,
        LocalDateTime menteeLastViewedAt
) {
    public static RoomResponse from(final Room room) {
        return new RoomResponse(
                room.getId(),
                MentorResponse.from(room.getMentor()),
                MenteeResponse.from(room.getMentee()),
                room.isStable(),
                room.getMentorLastViewedAt(),
                room.getMenteeLastViewedAt()
        );
    }
}