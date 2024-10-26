package com.develop_ping.union.user.domain;

import com.develop_ping.union.user.domain.entity.User;

public interface UserManager {
    User findById (Long userId);

    User findByEmail(String email);

    User findByToken(String token);

    User save(User user);

    void delete(User user);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
