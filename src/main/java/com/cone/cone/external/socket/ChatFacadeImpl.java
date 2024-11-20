package com.cone.cone.external.socket;

import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cone.cone.external.socket.constant.SocketIOEvent.MESSAGE;

@Service
@Transactional
public class ChatFacadeImpl implements ChatFacade{
    SocketService socketService;

    ChatFacadeImpl(SocketService socketService){
        this.socketService = socketService;
    }

    public void broadcastMessage(SocketIOClient client, Long roomId, SocketMessage message) {
        socketService.broadcastData(client, roomId, MESSAGE, message);
    }
}
