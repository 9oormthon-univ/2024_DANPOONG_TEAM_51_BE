package com.cone.cone.domain.mentorings.service;

import com.cone.cone.domain.mentorings.dto.request.*;
import com.cone.cone.domain.mentorings.dto.response.*;
import com.cone.cone.domain.mentorings.entity.*;
import com.cone.cone.domain.mentorings.repository.*;
import com.cone.cone.domain.room.entity.*;
import com.cone.cone.domain.room.repository.*;
import com.cone.cone.domain.user.entity.*;
import com.cone.cone.domain.user.repository.*;
import java.util.*;

import com.cone.cone.global.exception.CustomException;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import static com.cone.cone.domain.mentorings.code.MentoringExceptionCode.INVALID_REQUEST_USER;

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
        mentoringRepository.save(mentoring);
        return MentoringTimeResponse.of(mentoring.getMentoringTime());
    }

    @Override
    public void approveMentoring(Long mentorId, Long mentoringId) {
        val mentoring = mentoringRepository.findByIdOrThrow(mentoringId);
        if (!Objects.equals(mentoring.getRoom().getMentor().getId(), mentorId)) {
            throw new CustomException(INVALID_REQUEST_USER);
        }

        mentoring.approve();

        if (!mentoring.getRoom().getIsStable()) {
            mentoring.getRoom().markAsStable();
        }

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
