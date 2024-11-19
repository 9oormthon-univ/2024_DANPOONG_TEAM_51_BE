package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "mentees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String concernSummary;
    private String mentorPreference;
    private String concernDetail;

    @Builder
    private Mentee(User user) {
        this.user = user;
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
