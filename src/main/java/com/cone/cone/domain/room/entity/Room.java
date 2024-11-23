package com.cone.cone.domain.room.entity;

import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "rooms",
        uniqueConstraints = {
                @UniqueConstraint(name = "mentor_mentee_unique", columnNames = {"mentor_id", "mentee_id"})
        }
)
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

    @ColumnDefault("false")
    private Boolean isStable;

    @Column(nullable = false)
    private LocalDateTime mentorLastViewedAt;

    @Column(nullable = false)
    private LocalDateTime menteeLastViewedAt;

    @Builder
    private Room(Mentor mentor, Mentee mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
        this.isStable = false;
        this.mentorLastViewedAt = LocalDateTime.now();
        this.menteeLastViewedAt = LocalDateTime.now();
        this.markAsStable();
    }

    public void markAsStable() {
        this.isStable = true;
    }

    public void updateMentorLastViewedAt(LocalDateTime mentorLastViewedAt) {
        this.mentorLastViewedAt = mentorLastViewedAt;
    }

    public void updateMenteeLastViewedAt(LocalDateTime menteeLastViewedAt) {
        this.menteeLastViewedAt = menteeLastViewedAt;
    }
}