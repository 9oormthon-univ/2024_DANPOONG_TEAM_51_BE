package com.cone.cone.external.socket;

import com.corundumstudio.socketio.SocketIOClient;

public interface SocketService {
    void sendData (SocketIOClient client, Long roomId, String event, Object data);
    void broadcastData (SocketIOClient client, Long roomId, String event, Object data);
    void broadcastDataByRoomId (Long roomId, String event, Object data);
}
