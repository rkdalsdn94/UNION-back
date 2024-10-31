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
        log.info("사용자 ID로 검색 시도: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        log.info("이메일로 사용자 검색 시도: {}", email);
        return userRepository.findByEmailAndDeletedIsFalse(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByToken(String token) {
        log.info("토큰으로 사용자 검색 시도");
        return userRepository.findByTokenAndDeletedIsFalse(token)
                .orElseThrow(() -> new UserNotFoundException(token));
    }

    @Override
    @Transactional
    public User save(User user) {
        log.info("사용자 저장 시도: 닉네임 - {}", user.getNickname());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        log.info("사용자 삭제 시도: 닉네임 - {}", user.getNickname());
        user.deleteUser();
        userRepository.save(user);
        log.info("사용자 삭제 완료: 닉네임 - {}", user.getNickname());
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        log.info("이메일로 사용자 존재 여부 확인: {}", email);
        return userRepository.existsByEmailAndDeletedIsFalse(email);
    }

    @Override
    @Transactional
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNicknameAndDeletedIsFalse(nickname);
    }
}
