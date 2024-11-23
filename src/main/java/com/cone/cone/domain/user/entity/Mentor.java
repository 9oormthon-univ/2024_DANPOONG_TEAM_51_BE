package com.cone.cone.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private MentorStatus status;

    private String rejectReason;

    @Builder
    private Mentor(User user) {
        this.user = user;
        this.status = MentorStatus.INREVIEW;
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
