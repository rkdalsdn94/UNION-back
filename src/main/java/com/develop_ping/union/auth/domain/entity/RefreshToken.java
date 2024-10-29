package com.develop_ping.union.auth.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public void update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }
}
