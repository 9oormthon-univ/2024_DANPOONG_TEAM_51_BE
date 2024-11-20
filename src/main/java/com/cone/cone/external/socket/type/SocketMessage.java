package com.cone.cone.external.socket.type;

import lombok.Getter;
import lombok.Setter;

public record SocketMessage (
    Long senderId,
    String content
) {
}
