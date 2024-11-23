package com.cone.cone.external.socket.controller;

import com.cone.cone.domain.messages.service.MessageService;
import com.cone.cone.external.socket.ChatFacade;
import com.cone.cone.external.socket.SocketService;
import com.cone.cone.external.socket.dto.SignalingData;
import com.cone.cone.external.socket.dto.SocketMessage;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.cone.cone.domain.messages.entity.type.MessageType.TEXT;
import static com.cone.cone.domain.user.constant.UserConstant.SYSTEM_ID;
import static com.cone.cone.external.socket.constant.SocketIOEvent.*;

@Component
@Slf4j
public class SocketIOController {
   private final SocketService socketService;
   private final ChatFacade chatFacade;
   private final MessageService messageService;

   public SocketIOController(SocketIOServer server, ChatFacade chatFacade, SocketService socketService, MessageService messageService) {
      this.chatFacade = chatFacade;
      this.socketService = socketService;
      this.messageService = messageService;

      server.addConnectListener(onConnected());
      server.addDisconnectListener(onDisconnected());
      server.addEventListener(MESSAGE, SocketMessage.class, onMessage());
      server.addEventListener(PRE_OFFER, SignalingData.class, onPreOffer());
      server.addEventListener(OFFER, SignalingData.class, onOffer());
      server.addEventListener(ANSWER, SignalingData.class, onAnswer());
      server.addEventListener(CANDIDATE, SignalingData.class, onCandidate());
   }

   private DataListener<SocketMessage> onMessage() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - Sender[{}]: {}", MESSAGE, client.getSessionId(), data.senderId(), data.content());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         // 메시지 저장
         messageService.createMessage(Long.valueOf(roomId), data.senderId(), data.content(), TEXT);
         // 메시지 생성 날짜 추가
         chatFacade.broadcastMessage(client, Long.parseLong(roomId), SocketMessage.of(data.senderId(), data.content()));
      };
   }

   private DataListener<SignalingData> onPreOffer() {
      return (client, data, ack) -> {
         log.info("Event[{}] from Socket Id[{}] - {}", PRE_OFFER, client.getSessionId(), data.toString());
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         socketService.sendData(client, Long.parseLong(roomId), PRE_OFFER, data);
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
      return (client) -> {
         String roomId = client.getHandshakeData().getSingleUrlParam("roomId");
         client.leaveRoom(roomId);
         socketService.sendData(client, Long.parseLong(roomId), DISCONNECT, SocketMessage.of(SYSTEM_ID, "상대방이 퇴장했습니다"));
         log.info("Socket ID[{}] - Disconnected to chat module through", client.getSessionId());
      };
   }
}