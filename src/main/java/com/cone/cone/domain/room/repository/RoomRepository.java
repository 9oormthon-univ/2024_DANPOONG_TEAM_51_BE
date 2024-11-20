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

    @Query("select r from Room r where r.mentee.id = :menteeId and r.mentor.id = :mentorId")
    Optional<Room> findByMenteeIdAndMentorId(Long menteeId, Long mentorId);
}