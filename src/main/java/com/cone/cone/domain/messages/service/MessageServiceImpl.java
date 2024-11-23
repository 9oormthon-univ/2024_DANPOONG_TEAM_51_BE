package com.cone.cone.domain.messages.service;

import com.cone.cone.domain.messages.entity.Message;
import com.cone.cone.domain.messages.repository.MessageRepository;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.repository.RoomRepository;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.UserRepository;
import com.cone.cone.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomExceptionCode.NOT_FOUND_ROOM;
import static com.cone.cone.domain.user.code.UserExceptionCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public Message createMessage(Long roomId, Long senderId, String content) {
        final Room room = roomRepository.findByIdOrThrow(roomId);

        final User sender = userRepository.findByIdOrThrow(senderId);

        final Message newMessage = Message.builder()
                .room(room)
                .sender(sender)
                .content(content)
                .build();

        return messageRepository.save(newMessage);
    }

    public List<Message> getMessagesByRoomId(Long roomId) {
        final Room room = roomRepository.findByIdOrThrow(roomId);

        return messageRepository.findAllByRoom(room);
    }
}
