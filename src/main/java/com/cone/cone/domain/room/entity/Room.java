package com.cone.cone.domain.room.entity;

import com.cone.cone.domain.user.entity.Mentee;
import com.cone.cone.domain.user.entity.Mentor;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

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

    @Builder
    private Room(Mentor mentor, Mentee mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
    }

    public boolean markAsStable() {
        this.isStable = true;
        return true;
    }
}