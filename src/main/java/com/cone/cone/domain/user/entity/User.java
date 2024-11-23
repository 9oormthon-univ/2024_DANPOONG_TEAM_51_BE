package com.cone.cone.domain.user.entity;

import com.cone.cone.global.base.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Getter
public class User extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String profileImgUrl;

    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private PlatformType platformType;

    @Column(nullable = false, unique = true)
    private String platformId;

    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    private User(Role role, PlatformType platformType, String platformId, String username, String profileImgUrl) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.username = username;
        this.profileImgUrl = profileImgUrl;
        this.platformType = platformType;
        this.platformId = platformId;
        this.role = role;
    }

    public void changeRole(final Role role) {
        this.role = role;
    }
}
