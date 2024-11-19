package com.cone.cone.domain.room.dto;

import jakarta.validation.constraints.NotNull;

public record RoomCreateRequest(
        @NotNull
        Long mentorId,
        @NotNull
        Long menteeId
) {
}
