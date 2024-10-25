package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.domain.dto.UserCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;

public interface UserService {
    UserInfo signUp (UserCommand command);

    UserInfo updateUser (UserCommand commad);
}
