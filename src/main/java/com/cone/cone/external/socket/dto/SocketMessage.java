package com.cone.cone.external.socket.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record SocketMessage (
    Long senderId,
    String content,
    String createdAt
) {
    public static SocketMessage of(Long senderId, String content) {
        return new SocketMessage(senderId, content, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
