package com.develop_ping.union.user.domain;

public interface UserManager {
    User findById (Long userId);

    User findByEmail(String email);
}
