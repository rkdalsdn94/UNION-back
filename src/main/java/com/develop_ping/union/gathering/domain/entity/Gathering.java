package com.develop_ping.union.gathering.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.gathering.exception.GatheringPermissionDeniedException;
import com.develop_ping.union.gathering.exception.GatheringValidationException;
import com.develop_ping.union.gathering.exception.ParticipantLimitExceededException;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gatherings")
public class Gathering extends AuditingFields {

    @Builder
    private Gathering(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        Place place,
        String thumbnail
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
        this.views = 0L;
        this.recruited = false;
        this.thumbnail = thumbnail;
    }

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

    @Column(name = "views", nullable = false)
    private Long views;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "recruited", nullable = false)
    private boolean recruited;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gathering", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Party> parties = new ArrayList<>();

    public void updateGathering(Gathering entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.maxMember = entity.getMaxMember();
        this.gatheringDateTime = entity.getGatheringDateTime();
        this.place = entity.getPlace();
    }

    public void validateOwner(User user) {
        if (!isOwner(user)) {
            throw new GatheringPermissionDeniedException(user.getId(), this.id);
        }
    }

    public boolean isOwner(User user) {
        return getOwner().equals(user);
    }

    public User getOwner() {
        return parties.stream()
                      .filter(party -> party.getRole() == PartyRole.OWNER)
                      .map(Party::getUser)
                      .findFirst()
                      .orElseThrow(() -> new IllegalStateException("주최자가 존재하지 않습니다."));
    }

    public String getOwnerNickname() {
        return parties.stream()
                      .filter(party -> party.getRole() == PartyRole.OWNER)
                      .findFirst()
                      .map(party -> party.getUser().getNickname())
                      .orElse("Unknown Owner");
    }

    public void incrementCurrentMember() {
        if (this.currentMember >= this.maxMember) {
            throw new ParticipantLimitExceededException("모임의 최대 인원을 초과할 수 없습니다.");
        }
        this.currentMember++;
    }

    public void decrementCurrentMember() {
        if (this.currentMember > 0) {
            this.currentMember--;
        }
    }

    public void incrementViews() {
        this.views++;
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

    public boolean isFull() {
        return this.currentMember >= this.maxMember;
    }

    public void close() {
        this.recruited = true;
    }

    public void open() {
        this.recruited = false;
    }
}
