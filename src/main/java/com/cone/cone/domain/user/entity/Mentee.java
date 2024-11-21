package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

import static com.cone.cone.global.constant.DomainConstant.COMMA;

@Entity
@Table(name = "mentees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentee {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String concernSummary;

    private String concernDetail;

    private String mentorPreference;

    private String keywords;

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
        return Arrays.stream(keywords.split(COMMA))
                .map(String::trim)  // 각 항목에서 불필요한 공백을 제거
                .collect(Collectors.toList());
    }
}
