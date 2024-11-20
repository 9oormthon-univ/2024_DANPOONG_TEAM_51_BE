package com.cone.cone.external.socket.controller;

import com.cone.cone.external.socket.ChatFacade;
import com.cone.cone.external.socket.SocketService;
import com.cone.cone.external.socket.dto.SignalingData;
import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import static com.cone.cone.external.socket.constant.SocketIOEvent.*;

@Component
@Slf4j
public class SocketIOController {
   private final ChatFacade chatFacade;
   private final SocketService socketService;

   public SocketIOController(SocketIOServer server, ChatFacade chatFacade, SocketService socketService) {
      this.chatFacade = chatFacade;
      this.socketService = socketService;

      server.addConnectListener(onConnected());
      server.addDisconnectListener(onDisconnected());
      server.addEventListener(MESSAGE, SocketMessage.class, onMessage());
      server.addEventListener(OFFER, SignalingData.class, onOffer());
      server.addEventListener(ANSWER, SignalingData.class, onAnswer());
      server.addEventListener(CANDIDATE, SignalingData.class, onCandidate());
   }

   private DataListener<SocketMessage> onMessage() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - Sender[{}]: {}", MESSAGE, client.getSessionId(), data.senderId(), data.content());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         chatFacade.sendMessage(client, Long.parseLong(roomId), data);
      };
   }

   private DataListener<SignalingData> onOffer() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - {}", OFFER, client.getSessionId(), data.toString());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         socketService.sendData(client, Long.parseLong(roomId), OFFER, data);
      };
   }

   private DataListener<SignalingData> onAnswer() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - {}", ANSWER, client.getSessionId(), data.toString());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         socketService.sendData(client, Long.parseLong(roomId), ANSWER, data);
      };
   }

   private DataListener<SignalingData> onCandidate() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - {}", CANDIDATE, client.getSessionId(), data.toString());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         socketService.sendData(client, Long.parseLong(roomId), CANDIDATE, data);
      };
   }

   private ConnectListener onConnected() {
      return (client) -> {
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         log.info("Socket ID[{}] - Connected to chat module through", client.getSessionId());
         client.joinRoom(roomId);
         log.info("Room[{}]'s Participants: {}", roomId, client.getNamespace().getRoomOperations(roomId).getClients().stream().map(SocketIOClient::getSessionId)
         );
      };
   }

   private DisconnectListener onDisconnected() {
      return client -> {
         String room = client.getHandshakeData().getSingleUrlParam("roomId");
         client.leaveRoom(room);
         log.info("Socket ID[{}] - Disconnected to chat module through", client.getSessionId());
      };
   }
}