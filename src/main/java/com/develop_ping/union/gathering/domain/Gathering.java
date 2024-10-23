package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.gathering.exception.GatheringValidationException;
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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "max_member", nullable = false)
    private Integer maxMember;

    @Column(name = "current_member", nullable = false)
    private Integer currentMember;

    @Embedded
    private Place place;

    @Column(name = "gathering_date_time", nullable = false)
    private ZonedDateTime gatheringDateTime;

    @Builder
    private Gathering(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        Place place
    ) {
        validateGatheringDateTime(gatheringDateTime);
        validateGatheringMemberCheck(maxMember);

        this.id = id;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember != null ? currentMember : 1;
        this.gatheringDateTime = gatheringDateTime;
        this.place = place;
    }

    private void validateGatheringDateTime(ZonedDateTime gatheringDateTime) {
        if (gatheringDateTime.isBefore(ZonedDateTime.now().plusMinutes(29))) {
            throw new GatheringValidationException("모임 일시는 현재 시간에서 30분 이후로 설정해주세요.");
        }
    }

    private void validateGatheringMemberCheck(int currentParticipants) {
        if (currentParticipants < 2 || currentParticipants > 15) {
            throw new GatheringValidationException("모임은 최소 2명 이상 최대 15명까지 가능합니다.");
        }
    }

    // TODO: 모임 참가 시 인원 제한 검증 (동시성 처리, 일단 비관락 -> 추후 낙관락으로 개선)
    public void validateParticipantCapacity(int currentParticipants) {
        if (currentParticipants > maxMember) {
            throw new IllegalArgumentException("모임 인원이 초과되었습니다.");
        }
    }

    @Override
    public String toString() {
        return "Gathering{" +
            "content='" + content + '\'' +
            ", id=" + id +
            ", title='" + title + '\'' +
            ", maxMember=" + maxMember +
            ", currentMember=" + currentMember +
            ", place=" + place +
            ", gatheringDateTime=" + gatheringDateTime +
            '}';
    }
}
