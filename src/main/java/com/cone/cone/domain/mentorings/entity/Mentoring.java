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

    @Builder
    private Mentoring(Room room) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.status = MentoringStatus.INREVIEW;
        this.room = room;
    }

    public void updateTime(LocalDateTime time) {
        this.mentoringTime = time;
    }

    public void approve() {
        this.status = MentoringStatus.APPROVED;
    }

    public void reject(String rejectionReason) {
        this.status = MentoringStatus.REJECTED;
        this.rejectionReason = rejectionReason;
    }
  
    public void updateOriginalRecordFileName(String fileName) {
        this.originalRecordFileName = fileName
    }
}
