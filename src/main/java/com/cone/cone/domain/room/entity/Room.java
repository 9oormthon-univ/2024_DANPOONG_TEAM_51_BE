package com.cone.cone.domain.room.entity;

import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rooms")
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false, updatable = false)
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "mentee_id", nullable = false, updatable = false)
    private Mentee mentee;

    @Builder
    private Room(Mentor mentor, Mentee mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
    }
}