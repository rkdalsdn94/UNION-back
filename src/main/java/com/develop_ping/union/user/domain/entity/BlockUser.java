package com.develop_ping.union.user.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "block_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockUser extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 차단을 실행한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User blockingUser;

    // 차단된 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private User blockedUser;

    @Builder
    private BlockUser(User blockingUser, User blockedUser) {
        this.blockingUser = blockingUser;
        this.blockedUser = blockedUser;
    }

    public static BlockUser of (User blockingUser, User blockedUser){
        return BlockUser.builder()
                .blockedUser(blockedUser)
                .blockingUser(blockingUser)
                .build();
    }
}

