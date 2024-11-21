package com.cone.cone.domain.mentorings.entity;

public enum MentoringStatus {
   // 멘토가 신청을 검토 중
   INREVIEW,
   // 멘토가 신청을 수락, 멘토링 진행 전
   APPROVED,
   // 멘토가 신청을 거절
   REJECTED,
   // 멘토링 진행 후, 요약본 전달 전
   DONE,
   // 요약본 전달 후
   SUMMARIZED
}
