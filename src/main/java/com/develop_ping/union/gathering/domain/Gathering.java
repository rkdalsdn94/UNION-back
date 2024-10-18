package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gatherings")
public class Gathering extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "max_member", nullable = false)
    private Integer maxMember = 1;

    @Column(name = "gathering_date_time", nullable = false)
    private ZonedDateTime gatheringDateTime;

    @Builder
    private Gathering(String token, String title, String content, Integer maxMember, ZonedDateTime gatheringDateTime) {
        validateTitleLength(title);
        validateContentLength(content);
        validateGatheringDateTime(gatheringDateTime);
        validateGatheringMemberCheck(maxMember);

        this.token = token;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.gatheringDateTime = gatheringDateTime;
    }

    private void validateTitleLength(String title) {
        if (title.length() < 2 || title.length() > 50) {
            throw new IllegalArgumentException("모임 제목은 2자 이상 50자 이하로 입력해주세요.");
        }
    }

    private void validateContentLength(String content) {
        if (content.length() < 2 || content.length() > 1000) {
            throw new IllegalArgumentException("모임 내용은 2자 이상 1000자 이하로 입력해주세요.");
        }
    }

    private void validateGatheringDateTime(ZonedDateTime gatheringDateTime) {
        if (gatheringDateTime.isBefore(ZonedDateTime.now().plusMinutes(30))) {
            throw new IllegalArgumentException("모임 일시는 현재 시간에서 30분 이후로 설정해주세요.");
        }
    }

    private void validateGatheringMemberCheck(int currentParticipants) {
        if (currentParticipants < 2 || currentParticipants > 15) {
            throw new IllegalArgumentException("모임은 최소 2명 이상 최대 15명까지 가능합니다.");
        }
    }

    // TODO: 모임 참가 시 인원 제한 검증 (동시성 처리, 일단 비관락 -> 추후 낙관락으로 개선)
    public void validateParticipantCapacity(int currentParticipants) {
        if (currentParticipants > maxMember) {
            throw new IllegalArgumentException("모임 인원이 초과되었습니다.");
        }
    }
}
