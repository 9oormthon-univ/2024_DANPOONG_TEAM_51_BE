package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

import static com.cone.cone.global.constant.DomainConstant.COMMA;

@Entity
@Table(name = "mentors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mentor {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column
    private String resume;

    @Column
    private String introduction;

    private String keywords;

    @Column(nullable = false) @Enumerated(value = EnumType.STRING)
    private MentorStatus mentorStatus;

    private String rejectReason;

    @Builder
    private Mentor(User user) {
        this.user = user;
        this.mentorStatus = MentorStatus.INREVIEW;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getProfileImgUrl() {
        return user.getProfileImgUrl();
    }

    public List<String> getKeywords() {
        if(keywords == null) {
            return null;
        }
        return Arrays.stream(keywords.split(COMMA))
                .map(String::trim)  // 각 항목에서 불필요한 공백을 제거
                .collect(Collectors.toList());
    }
}
