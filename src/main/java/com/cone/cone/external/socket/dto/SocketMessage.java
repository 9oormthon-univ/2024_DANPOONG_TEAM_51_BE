package com.cone.cone.external.socket.dto;

public record SocketMessage (
    Long senderId,
    String content
) {
}
