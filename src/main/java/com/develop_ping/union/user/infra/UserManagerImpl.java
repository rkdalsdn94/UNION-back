package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.User;
import com.develop_ping.union.user.domain.UserManager;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {
    @Override
    public User findById(Long userId) {
        return User.builder()
                .id(userId)
                .build();
    }
}
