package com.develop_ping.union.reaction.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reaction extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @Builder
    private Reaction(Long id, Long userId, ReactionType type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }
}
