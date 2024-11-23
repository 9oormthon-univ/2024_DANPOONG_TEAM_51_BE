package com.cone.cone.external.socket.dto;

import java.time.LocalDateTime;

public record SocketMessage (
    Long senderId,
    String content,
    LocalDateTime createdAt
) {
}
