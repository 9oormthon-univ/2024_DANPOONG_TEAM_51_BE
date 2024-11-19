package com.cone.cone.external.socket;

import com.cone.cone.domain.messages.entity.Message;
import com.corundumstudio.socketio.SocketIOClient;

public interface SocketService {
    void sendData (SocketIOClient client, Long roomId, String event, Object data);
    void broadcastData (SocketIOClient client, Long roomId, String event, Object data);
}
