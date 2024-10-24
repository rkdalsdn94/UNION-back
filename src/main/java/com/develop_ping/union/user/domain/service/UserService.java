package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.domain.dto.SignUpCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;

public interface UserService {
    UserInfo signUp (SignUpCommand command);
}
