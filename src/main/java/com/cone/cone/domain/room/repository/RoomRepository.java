package com.cone.cone.domain.room.repository;

import com.cone.cone.domain.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByMentor_Id(Long mentorId);
    List<Room> findAllByMentee_Id(Long menteeId);
}