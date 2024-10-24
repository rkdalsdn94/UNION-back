package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public User findById(Long userId) {
        log.info("ID로 유저 검색 시도");
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        log.info("Email로 유저 검색 시도");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User save(User user) {
        log.info("유저 저장: {}", user.getNickname());
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        log.info("유저 삭제: {}", user.getNickname());
        userRepository.delete(user);
    }
}
