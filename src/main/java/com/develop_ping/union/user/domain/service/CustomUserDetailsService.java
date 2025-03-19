package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.exception.UserNotFoundException;
import com.develop_ping.union.user.infra.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        return userRepository.findByNicknameAndDeletedIsFalse(nickname)
                .orElseThrow(() -> new UserNotFoundException(nickname));
    }

    // 추가: Token을 이용한 사용자 로드 메서드
    @Transactional
    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        return userRepository.findByTokenAndDeletedIsFalse(token)
                .orElseThrow(() -> new UserNotFoundException(token));
    }
}
