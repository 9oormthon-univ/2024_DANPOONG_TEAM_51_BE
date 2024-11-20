package com.cone.cone.external.socket;

import com.cone.cone.external.socket.type.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;

public interface ChatFacade {
    void sendMessage (SocketIOClient client, Long roomId, SocketMessage message);
}