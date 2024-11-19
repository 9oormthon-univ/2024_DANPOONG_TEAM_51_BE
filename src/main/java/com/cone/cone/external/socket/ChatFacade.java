package com.cone.cone.external.socket;

import com.cone.cone.domain.messages.entity.Message;
import com.corundumstudio.socketio.SocketIOClient;

public interface ChatFacade {
    void sendMessage (SocketIOClient client, Long roomId, Message message);
}