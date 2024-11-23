package com.cone.cone.domain.messages.dto;

public record MessageSimpleResponse (
        Long id,
        Long roomId,
        String senderName,
        String content,
        boolean isViewed
) {

}
