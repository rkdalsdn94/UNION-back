package com.develop_ping.union.party.domain.dto;

import com.develop_ping.union.party.domain.entity.Party;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PartyInfo {

    private final Long id;
    private final Long userId;
    private final Long gatheringId;
    private final String role;

    @Builder
    private PartyInfo(Long id, Long userId, Long gatheringId, String role) {
        this.id = id;
        this.userId = userId;
        this.gatheringId = gatheringId;
        this.role = role;
    }

    public static PartyInfo of(Party party) {
        return PartyInfo.builder()
                .id(party.getId())
                .userId(party.getUserId())
                .gatheringId(party.getGatheringId())
                .role(String.valueOf(party.getRole()))
                .build();
    }

    @Override
    public String toString() {
        return "PartyInfo{" +
            "gatheringId=" + gatheringId +
            ", id=" + id +
            ", userId=" + userId +
            ", role='" + role + '\'' +
            '}';
    }
}
