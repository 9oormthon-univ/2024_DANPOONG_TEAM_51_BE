package com.cone.cone.domain.mentorings.entity;

import com.cone.cone.domain.room.entity.*;
import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Entity
@Table(name = "mentorings")
@Getter
public class Mentoring {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Enumerated(EnumType.STRING)
    private MentoringStatus status;
    private String rejectionReason;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    private LocalDateTime mentoringTime;
    private int rating;
    private int reviewText;
    private String summary;
}
