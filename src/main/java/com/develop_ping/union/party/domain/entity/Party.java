package com.develop_ping.union.party.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "parties")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Party extends AuditingFields {
    // TODO: 연관 관계 고민

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long gatheringId;

    @Enumerated(EnumType.STRING)
    private PartyRole role;

    @Builder
    private Party(Long id, Long userId, Long gatheringId, PartyRole role) {
        this.id = id;
        this.userId = userId;
        this.gatheringId = gatheringId;
        this.role = role;
    }
}
