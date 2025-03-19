package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.user.domain.dto.UserCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.dto.UserStatCommand;
import com.develop_ping.union.user.domain.dto.UserStatInfo;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface UserService {
    UserInfo signUp (UserCommand command);

    UserInfo updateUser (UserCommand commad, User user);

    void signOut(User user);

    UserInfo searchUser (User user, String token);

    void deleteUser(User user);
    void blockUser(User user, String userToken);
    void unblockUser(User user, String userToken);

    List<UserInfo> readBlockedUsers (User user);

    UserStatInfo readUserStat(UserStatCommand command);
}
