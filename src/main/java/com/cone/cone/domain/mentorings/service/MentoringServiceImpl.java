package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.entity.*;
import com.cone.cone.domain.mentorings.repository.*;
import com.cone.cone.domain.room.entity.*;
import com.cone.cone.domain.room.repository.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService {
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;
    private final MentoringRepository mentoringRepository;
    private final RoomRepository roomRepository;

    @Transactional
    @Override
    public MentoringIdResponse requestMentoring(final Long menteeId, final MentoringRequest request) {
        val mentee = menteeRepository.findByIdOrThrow(menteeId);
        val mentor = mentorRepository.findByIdOrThrow(request.mentorId());
        val room = roomRepository.findByMenteeAndMentor(mentee, mentor).orElse(
                createNewRoom(mentee, mentor)
        );
        var mentoring = Mentoring.builder().room(room).build();

        mentoring = mentoringRepository.save(mentoring);
        return MentoringIdResponse.of(mentoring.getId());
    }

    private Room createNewRoom(Mentee mentee, Mentor mentor) {
        val room = Room.builder()
                .mentee(mentee)
                .mentor(mentor)
                .build();
        roomRepository.save(room);
        return room;
    }
}
