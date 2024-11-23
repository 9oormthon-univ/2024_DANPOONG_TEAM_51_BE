package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.entity.*;
import com.cone.cone.domain.mentorings.repository.*;
import com.cone.cone.domain.messages.service.MessageService;
import com.cone.cone.domain.room.entity.*;
import com.cone.cone.domain.room.repository.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import com.cone.cone.external.aws.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

import com.cone.cone.external.socket.ChatFacade;
import com.cone.cone.external.socket.dto.SocketMessage;
import com.cone.cone.global.exception.CustomException;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import static com.cone.cone.domain.mentorings.code.MentoringExceptionCode.INVALID_REQUEST_USER;
import static com.cone.cone.domain.messages.constant.MessageConstant.*;
import static com.cone.cone.domain.messages.entity.type.MessageType.NOTICE;
import static com.cone.cone.domain.user.constant.UserConstant.SYSTEM_ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;
    private final MentoringRepository mentoringRepository;
    private final RoomRepository roomRepository;
    private final MessageService messageService;
    private final ChatFacade chatFacade;

    @Transactional
    @Override
    public MentoringIdResponse requestMentoring(final Long menteeId, final MentoringRequest request) {
        val mentee = menteeRepository.findByIdOrThrow(menteeId);
        val mentor = mentorRepository.findByIdOrThrow(request.mentorId());
        val room = roomRepository.findByMenteeAndMentor(mentee, mentor).orElse(
                createRoom(mentee, mentor)
        );

        var mentoring = Mentoring.builder().room(room).build();

        mentoring = mentoringRepository.save(mentoring);
        return MentoringIdResponse.of(mentoring.getId());
    }

    private Room createRoom(Mentee mentee, Mentor mentor) {
        val newRoom = Room.builder()
                .mentee(mentee)
                .mentor(mentor)
                .build();

        return roomRepository.save(newRoom);
    }

    public List<MenteeMentoringResponse> getMentoringsByMenteeId(final Long menteeId) {
        val mentorings = mentoringRepository.findAllByMenteeIdOrderCreatedAtDesc(menteeId);

        return mentorings.stream()
                .map(mentoring -> MenteeMentoringResponse.of(mentoring.getId(), mentoring.getRoom().getMentor()))
                .toList();
    }

    public List<MentorMentoringResponse> getMentoringsByMentorId(final Long mentorId) {
        val mentorings = mentoringRepository.findAllByMentorIdOrderCreatedAtDesc(mentorId);

        return mentorings.stream()
                .map(mentoring -> MentorMentoringResponse.of(mentoring.getId(), mentoring.getRoom().getMentee()))
                .toList();
    }

    @Override
    public MentoringTimeResponse bookingMentoring(Long userId, Long mentoringId, MentoringBookingRequest request) {
        val mentoring = mentoringRepository.findByIdOrThrow(mentoringId);
        if (!Objects.equals(mentoring.getRoom().getMentor().getId(), userId) && !Objects.equals(mentoring.getRoom().getMentee().getId(), userId)) {
            throw new CustomException(INVALID_REQUEST_USER);
        }

        mentoring.updateTime(request.mentoringTime());

        // 멘토링 시간 예약 메시지 생성 및 전송
        val message = MENTORING_TIME_BOOKED + request.mentoringTime().format(DateTimeFormatter.ofPattern("MM월 dd일(E) a HH:mm"));
        messageService.createMessage(
                mentoring.getRoom().getId(),
                SYSTEM_ID,
                message,
                NOTICE
        );
        SocketMessage socketMessage = SocketMessage.of(SYSTEM_ID, message);
        chatFacade.broadcastMessageByRoomId(mentoring.getRoom().getId(), socketMessage);

        mentoringRepository.save(mentoring);
        return MentoringTimeResponse.of(mentoring.getMentoringTime());
    }

    @Override
    public void approveMentoring(Long mentorId, Long mentoringId) {
        val mentoring = mentoringRepository.findByIdOrThrow(mentoringId);

        if (!Objects.equals(mentoring.getRoom().getMentor().getId(), mentorId)) {
            throw new CustomException(INVALID_REQUEST_USER);
        }

        if (!mentoring.getRoom().getIsStable()) {
            mentoring.getRoom().markAsStable();
            // 채팅방 생성 메시지 생성 및 전송
            messageService.createMessage(mentoring.getRoom().getId(), SYSTEM_ID, ROOM_STABLED, NOTICE);
            SocketMessage socketMessage = SocketMessage.of(SYSTEM_ID, ROOM_STABLED);
            chatFacade.broadcastMessageByRoomId(mentoring.getRoom().getId(), socketMessage);
        }

        // 멘토링 수락 메시지 생성 및 전송
        messageService.createMessage(mentoring.getRoom().getId(), SYSTEM_ID, MENTORING_APPROVED, NOTICE);
        SocketMessage socketMessage = SocketMessage.of(SYSTEM_ID, MENTORING_APPROVED);
        chatFacade.broadcastMessageByRoomId(mentoring.getRoom().getId(), socketMessage);

        mentoring.approve();
        mentoringRepository.save(mentoring);
    }

    @Override
    public void rejectMentoring(Long mentorId, Long mentoringId, MentoringRejectRequest request) {
        val mentoring = mentoringRepository.findByIdOrThrow(mentoringId);
        if (!Objects.equals(mentoring.getRoom().getMentor().getId(), mentorId)) {
            throw new CustomException(INVALID_REQUEST_USER);
        }

        mentoring.reject(request.rejectReason());
        mentoringRepository.save(mentoring);
    }
}
