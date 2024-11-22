package com.cone.cone.domain.mentorings.entity;

import com.cone.cone.domain.room.entity.*;
import com.cone.cone.global.base.*;
import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Entity
@Table(name = "mentorings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentoring extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MentoringStatus status;

    private String rejectionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    private LocalDateTime mentoringTime;

    private String originalRecordFileName;

    private String summarizedRecordFileName;

    private Duration recordDuration;

    @Builder
    private Mentoring(Room room) {
        this.status = MentoringStatus.INREVIEW;
        this.room = room;
    }

    public void updateOriginalRecordFileName(String fileName) {
        this.originalRecordFileName = fileName;
    }
}
