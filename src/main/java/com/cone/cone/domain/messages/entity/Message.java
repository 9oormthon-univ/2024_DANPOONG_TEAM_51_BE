package com.cone.cone.domain.messages.entity;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "messages")
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, updatable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, updatable = false)
    private User sender;

    @Column(nullable = false, updatable = false)
    private String content;

    @Column(nullable = false)
    private boolean isViewed;

    @Builder
    private Message(Room room, User sender, String content) {
        this.room = room;
        this.sender = sender;
        this.content = content;
    }

    public void markAsViewed() {
        this.isViewed = true;
    }
}