package com.cone.cone.external.socket;

import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;

public interface ChatFacade {
    void broadcastMessage (SocketIOClient client, Long roomId, SocketMessage message);
}