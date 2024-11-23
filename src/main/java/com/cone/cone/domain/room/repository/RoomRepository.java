package com.cone.cone.domain.room.repository;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import com.cone.cone.global.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static com.cone.cone.domain.room.code.RoomExceptionCode.NOT_FOUND_ROOM;

public interface RoomRepository extends JpaRepository<Room, Long> {
    default Room findByIdOrThrow(final Long id) {
        return findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_ROOM));
    }

    boolean existsByMentorAndMentee(Mentor mentor, Mentee mentee);

    List<Room> findAllByMentor(Mentor mentor);

    List<Room> findAllByMentee(Mentee mentee);

    Optional<Room> findByMenteeAndMentor(Mentee mentee, Mentor mentor);
}