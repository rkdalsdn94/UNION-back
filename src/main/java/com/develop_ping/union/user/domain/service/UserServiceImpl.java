package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.user.domain.dto.SignUpCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final OauthUserManager oauthUserManager;
    @Override
    public void signUp(SignUpCommand command) {

    }
}
