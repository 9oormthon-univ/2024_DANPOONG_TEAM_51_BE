package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mentors")
public class Mentor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private AuditStatus auditStatus;
    private String rejectReason;



}
