package com.develop_ping.union.user.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.dto.SignUpCommand;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.*;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingFields implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    // UUID 기반의 고유 토큰, 패스워드 용으로도 사용
    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(length = 50)
    private String description;

    @Column(length = 50, nullable = false)
    private String profileImage;

    @Column(nullable = false, length = 50)
    private String univName;

    @Builder
    private User(String email, String token, String nickname, String description, String profileImage, String univName) {
        this.email = email;
        this.token = token;
        this.nickname = nickname;
        this.description = description;
        this.profileImage = profileImage;
        this.univName = univName;
    }

    public static User of (SignUpCommand command, String email, String profileImage) {
        return User.builder()
                .email(email)
                .profileImage(profileImage)
                .token(UUID.randomUUID().toString())
                .nickname(command.getNickname())
                .univName(command.getUnivName())
                .description(command.getDescription())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public String getPassword() {
        return token;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


