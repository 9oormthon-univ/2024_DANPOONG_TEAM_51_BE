package com.cone.cone.domain.messages.dto;

import com.cone.cone.domain.messages.entity.Message;

public record MessageResponse(
        Long id,
        Long roomId,
        String senderName,
        String content
) {
    public static MessageResponse from(final Message message) {
        return new MessageResponse(
                message.getId(),
                message.getRoom().getId(),
                message.getSender().getName(),
                message.getContent()
        );
    }
}
