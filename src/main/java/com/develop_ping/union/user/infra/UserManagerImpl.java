package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;
    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
