package com.cone.cone.external.socket;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SocketServiceImpl implements SocketService {
    public void sendData(SocketIOClient client, Long roomId, String event, Object data) {
        for (SocketIOClient c : client.getNamespace().getRoomOperations(roomId.toString()).getClients()) {
            if (!c.getSessionId().equals(client.getSessionId())) {
                c.sendEvent(event, data);
            }
        }
    }

    public void broadcastData(SocketIOClient client, Long roomId, String event, Object data) {
        client.getNamespace().getRoomOperations(roomId.toString()).sendEvent(event, data);
    }
}
