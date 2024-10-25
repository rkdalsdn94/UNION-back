package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.domain.dto.UserCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.entity.User;

public interface UserService {
    UserInfo signUp (UserCommand command);

    UserInfo updateUser (UserCommand commad, User user);

    void signOut(User user);

    UserInfo searchUser (String token);
}
