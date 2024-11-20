package com.cone.cone.external.socket.controller;

import com.cone.cone.external.socket.ChatFacade;
import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import static com.cone.cone.external.socket.constant.SocketIOEvent.MESSAGE;

@Component
@Slf4j
public class SocketIOController {
   private final ChatFacade chatFacade;

   public SocketIOController(SocketIOServer server, ChatFacade chatFacade) {
      this.chatFacade = chatFacade;

      server.addConnectListener(onConnected());
      server.addDisconnectListener(onDisconnected());
      server.addEventListener(MESSAGE, SocketMessage.class, onMessage());
   }

   private DataListener<SocketMessage> onMessage() {
      return (client, data, ack) -> {
         String room = client.getHandshakeData().getSingleUrlParam("room");
         log.info("Event[{}] from Socket Id[{}] - Sender[{}]: {}", MESSAGE, client.getSessionId(), data.senderId(), data.content());
         chatFacade.sendMessage(client, Long.parseLong(room), data);
      };
   }

   private ConnectListener onConnected() {
      return (client) -> {
         String room = client.getHandshakeData().getSingleUrlParam("room");
         log.info("Socket ID[{}] - Connected to chat module through", client.getSessionId());
         client.joinRoom(room);
         log.info("Room[{}]'s Participants: {}", room, client.getNamespace().getRoomOperations(room).getClients().stream().map(SocketIOClient::getSessionId)
         );
      };
   }

   private DisconnectListener onDisconnected() {
      return client -> {
         String room = client.getHandshakeData().getSingleUrlParam("room");
         client.leaveRoom(room);
         log.info("Socket ID[{}] - Disconnected to chat module through", client.getSessionId());
      };
   }
}