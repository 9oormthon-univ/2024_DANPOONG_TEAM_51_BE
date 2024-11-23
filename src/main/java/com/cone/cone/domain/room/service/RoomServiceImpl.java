package com.cone.cone.domain.room.service;

import com.cone.cone.domain.room.dto.RoomCreateRequest;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.room.repository.RoomRepository;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.domain.user.repository.MenteeRepository;
import com.cone.cone.domain.user.repository.MentorRepository;
import com.cone.cone.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.cone.cone.domain.room.code.RoomExceptionCode.ALREADY_EXIST_ROOM;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    @Transactional
    public Room createRoom(final RoomCreateRequest request) {
        final Mentor mentor = mentorRepository.findByIdOrThrow(request.mentorId());

        final Mentee mentee = menteeRepository.findByIdOrThrow(request.menteeId());

        if (roomRepository.existsByMentorAndMentee(mentor, mentee)) {
            throw new CustomException(ALREADY_EXIST_ROOM);
        }

        final Room newRoom = Room.builder()
                .mentor(mentor)
                .mentee(mentee)
                .build();

        return roomRepository.save(newRoom);
    }

    public List<Room> getRoomsByMentorId(Long mentorId) {
        final Mentor mentor = mentorRepository.findByIdOrThrow(mentorId);

        return roomRepository.findAllByMentor(mentor);
    }

    public List<Room> getRoomsByMenteeId(Long menteeId) {
        final Mentee mentee = menteeRepository.findByIdOrThrow(menteeId);

        return roomRepository.findAllByMentee(mentee);
    }
}
