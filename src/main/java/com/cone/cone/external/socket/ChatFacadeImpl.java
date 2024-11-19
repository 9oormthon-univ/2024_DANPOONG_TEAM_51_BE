package com.cone.cone.external.socket;

import com.cone.cone.domain.messages.entity.Message;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.cone.cone.external.socket.constant.SocketIOEvent.MESSAGE;

@Component
@RequiredArgsConstructor
@Transactional
public class ChatFacadeImpl implements ChatFacade{
    SocketService socketService;
    public void sendMessage(SocketIOClient client, Long roomId, Message message) {
        socketService.sendData(client, roomId, MESSAGE, message);
    }
}
