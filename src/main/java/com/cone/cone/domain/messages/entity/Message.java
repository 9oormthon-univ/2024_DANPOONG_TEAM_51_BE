package com.cone.cone.domain.messages.entity;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.entity.User;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, updatable = false)
    private Room room;

    @ManyToOne
    @Column(name = "sender_id", nullable = false, updatable = false)
    private User sender;

    @Column(nullable = false, updatable = false)
    private String content;
}