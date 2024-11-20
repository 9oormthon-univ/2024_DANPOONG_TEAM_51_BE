package com.cone.cone.domain.messages.service;

import com.cone.cone.domain.messages.entity.Message;
import com.cone.cone.domain.messages.repository.MessageRepository;
import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.repository.RoomRepository;
import com.cone.cone.domain.room.service.RoomService;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.entity.User;
import com.cone.cone.domain.user.repository.MenteeRepository;
import com.cone.cone.domain.user.repository.MentorRepository;
import com.cone.cone.domain.user.repository.UserRepository;
import com.cone.cone.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomExceptionCode.ALREADY_EXIST_ROOM;
import static com.cone.cone.domain.room.code.RoomExceptionCode.NOT_FOUND_ROOM;
import static com.cone.cone.domain.user.code.MenteeExceptionCode.NOT_FOUND_MENTEE;
import static com.cone.cone.domain.user.code.MentorExceptionCode.NOT_FOUND_MENTOR;
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
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_ROOM));

        final User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        final Message newMessage = Message.builder()
                .room(room)
                .sender(sender)
                .content(content)
                .build();

        return messageRepository.save(newMessage);
    }

    public List<Message> getMessagesByRoomId(Long roomId) {
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_ROOM));

        return messageRepository.findAllByRoom(room);
    }
}
