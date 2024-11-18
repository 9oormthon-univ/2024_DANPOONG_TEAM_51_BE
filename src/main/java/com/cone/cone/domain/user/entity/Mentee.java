package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mentees")
public class Mentee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String concernSummary;
    private String mentorPreference;
    private String concernDetail;
}
