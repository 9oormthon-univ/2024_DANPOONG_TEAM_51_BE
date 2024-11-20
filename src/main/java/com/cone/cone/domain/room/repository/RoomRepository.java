package com.cone.cone.domain.room.repository;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByMentorAndMentee(Mentor mentor, Mentee mentee);

    List<Room> findAllByMentor(Mentor mentor);

    List<Room> findAllByMentee(Mentee mentee);

    Optional<Room> findByMenteeAndMentor(Mentee mentee, Mentor mentor);
}