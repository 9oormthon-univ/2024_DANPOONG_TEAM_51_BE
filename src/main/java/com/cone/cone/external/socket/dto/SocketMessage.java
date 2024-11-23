package com.cone.cone.external.socket.dto;

import java.time.LocalDateTime;

public record SocketMessage (
    Long senderId,
    String content,
    LocalDateTime createdAt
) {
    public static SocketMessage of(Long senderId, String content) {
        return new SocketMessage(senderId, content, LocalDateTime.now());
    }
}
