package com.cone.cone.domain.user.entity;

import static com.cone.cone.global.constant.DomainConstant.COMMA;

import com.cone.cone.global.base.BaseTime;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;
import lombok.*;

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
