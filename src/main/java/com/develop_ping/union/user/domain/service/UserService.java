package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.domain.dto.SignUpCommand;

public interface UserService {
    void signUp (SignUpCommand command);
}
