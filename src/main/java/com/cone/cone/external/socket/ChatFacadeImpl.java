package com.cone.cone.external.socket;

import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cone.cone.external.socket.constant.SocketIOEvent.MESSAGE;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatFacadeImpl implements ChatFacade {
    private final SocketService socketService;

    @Override
    public void broadcastMessage(SocketIOClient client, Long roomId, SocketMessage message) {
        socketService.broadcastData(client, roomId, MESSAGE, message);
    }

    @Override
    public void broadcastMessageByRoomId(Long roomId, SocketMessage message) {
        socketService.broadcastDataByRoomId(roomId, MESSAGE, message);
    }
}
