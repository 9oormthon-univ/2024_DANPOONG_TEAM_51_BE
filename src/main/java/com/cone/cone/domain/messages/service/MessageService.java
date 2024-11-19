package com.cone.cone.domain.messages.service;

import com.cone.cone.domain.messages.entity.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(Long roomId, Long senderId, String content);
    List<Message> getMessagesByRoomId(Long roomId);
}