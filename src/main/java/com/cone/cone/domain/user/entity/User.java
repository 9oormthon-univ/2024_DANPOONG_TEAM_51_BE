package com.cone.cone.domain.user.entity;

import static com.cone.cone.global.constant.DomainConstant.COMMA;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.*;
import java.util.stream.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private PlatformType platformType;
    @Column(nullable = false, unique = true)
    private String platformId;
    private String username;
    private String profileImgUrl;
    private String keywords; // 온보딩에서 입력받을 수 없어 임시로 nullable하게 설정

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

    public List<String> getKeywords() {
        return Arrays.stream(keywords.split(COMMA))
                .map(String::trim)  // 각 항목에서 불필요한 공백을 제거
                .collect(Collectors.toList());
    }
}
