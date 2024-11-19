package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "mentors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentor {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private AuditStatus auditStatus;
    private String rejectReason;

    @Builder
    private Mentor(User user) {
        this.user = user;
        this.auditStatus = AuditStatus.INREVIEW;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getProfileImgUrl() {
        return user.getProfileImgUrl();
    }

    public List<String> getKeywords() {
        return user.getKeywords();
    }
}
