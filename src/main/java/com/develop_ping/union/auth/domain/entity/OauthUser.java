package com.develop_ping.union.auth.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "oauth_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthUser extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String profileImage;

    // UUID 기반의 고유 토큰
    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @Column(nullable = false, unique = true, updatable = false)
    private String oauthAccessToken;

    @Column(nullable = false, length = 30)
    private String provider;

    @Builder
    public OauthUser(String email, String profileImage, String token, String provider, String oauthAccessToken) {
        this.email = email;
        this.profileImage = profileImage;
        this.token = token;
        this.provider = provider;
        this.oauthAccessToken = oauthAccessToken;
    }
}
