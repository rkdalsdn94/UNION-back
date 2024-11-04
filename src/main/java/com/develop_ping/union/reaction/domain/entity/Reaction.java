package com.develop_ping.union.reaction.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private User user;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @Builder
    private Reaction(User user, Long targetId, ReactionType type) {
        this.user = user;
        this.targetId = targetId;
        this.type = type;
    }

    public static Reaction of(User user, Long targetId, ReactionType type) {
        return Reaction.builder()
                .user(user)
                .targetId(targetId)
                .type(type)
                .build();
    }
}
