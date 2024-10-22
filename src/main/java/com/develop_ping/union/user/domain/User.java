package com.develop_ping.union.user.domain;

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
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    // Spring Security 관련 필드
    private String password;

    // UUID 기반의 고유 토큰
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

    // 자동으로 생성 시간 설정
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    // 자동으로 수정 시간 설정
    @UpdateTimestamp
    @Column(nullable = false)
    private ZonedDateTime updatedAt;

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
        return password;
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


