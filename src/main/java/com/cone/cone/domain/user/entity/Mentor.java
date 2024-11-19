package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mentors")
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



}
