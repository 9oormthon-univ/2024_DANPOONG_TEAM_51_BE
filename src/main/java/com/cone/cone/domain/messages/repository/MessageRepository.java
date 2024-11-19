package com.cone.cone.domain.messages.repository;

import com.cone.cone.domain.messages.entity.Message;
import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRoom (Room room);
}