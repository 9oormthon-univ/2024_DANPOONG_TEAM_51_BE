package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private PlatformType platformType;
    @Column(nullable = false)
    private String platformId;
    private String username;
    private String profileImgUrl;
    @Column(nullable = false)
    private String keyword;

    @Builder
    private User(Role role, PlatformType platformType, String platformId, String username, String profileImgUrl) {
        this.role = role;
        this.platformType = platformType;
        this.platformId = platformId;
        this.username = username;
        this.profileImgUrl = profileImgUrl;
    }

    public void changeRole(final Role role) {
        this.role = role;
    }
}
