package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mentees")
public class Mentee {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String concernSummary;
    private String mentorPreference;
    private String concernDetail;
}
