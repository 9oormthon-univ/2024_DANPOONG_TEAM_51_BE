package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mentors")
@Getter
public class Mentor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String keyword;

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
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getProfileImgUrl() {
        return user.getProfileImgUrl();
    }


}
