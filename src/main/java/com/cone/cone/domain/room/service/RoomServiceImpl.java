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

import java.util.List;

import static com.cone.cone.domain.user.code.MenteeExceptionCode.NOT_FOUND_MENTEE;
import static com.cone.cone.domain.user.code.MentorExceptionCode.NOT_FOUND_MENTOR;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    public Room createRoom(final RoomCreateRequest request) {
        final Mentor mentor = mentorRepository.findById(request.mentorId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_MENTOR));

        final Mentee mentee = menteeRepository.findById(request.menteeId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_MENTEE));

        final Room newRoom = Room.builder()
                .mentor(mentor)
                .mentee(mentee)
                .build();

        return roomRepository.save(newRoom);
    }

    public List<Room> getRoomsByMentorId(Long mentorId) {
        return roomRepository.findAllByMentor_Id(mentorId);
    }

    public List<Room> getRoomsByMenteeId(Long menteeId) {
        return roomRepository.findAllByMentee_Id(menteeId);
    }
}
