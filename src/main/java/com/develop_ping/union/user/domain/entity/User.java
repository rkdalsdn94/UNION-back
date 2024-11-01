package com.develop_ping.union.user.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.dto.UserCommand;
import com.develop_ping.union.auth.domain.entity.RefreshToken;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import java.util.*;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingFields implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @Column(nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(length = 50)
    private String description;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false, length = 30)
    private String univName;

    @Column(nullable = false, length = 30)
    private String provider;

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    private User(String email, String token, String nickname, String description, String profileImage, String univName, String provider, boolean deleted) {
        this.email = email;
        this.token = token;
        this.nickname = nickname;
        this.description = description;
        this.profileImage = profileImage;
        this.univName = univName;
        this.provider = provider;
        this.deleted = deleted;
    }

    public static User of(UserCommand command, String email, String profileImage, String provider) {
        return User.builder()
                .email(email)
                .profileImage(profileImage)
                .provider(provider)
                .token(UUID.randomUUID().toString())
                .nickname(command.getNickname())
                .univName(command.getUnivName())
                .description(command.getDescription())
                .deleted(false)
                .build();
    }

    public void deleteUser () {
        this.deleted = true;
    }

    public void update(String nickname, String description, String profileImage) {
        if (nickname != null && !nickname.isEmpty()) {
            this.nickname = nickname;
        }
        if (description != null) {
            this.description = description;
        }
        if (profileImage != null && !profileImage.isEmpty()) {
            this.profileImage = profileImage;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
