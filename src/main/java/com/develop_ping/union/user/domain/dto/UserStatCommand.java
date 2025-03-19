package com.develop_ping.union.user.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStatCommand {
    private String userToken;
    private User user;

    @Builder
    private UserStatCommand(String userToken, User user) {
        this.userToken = userToken;
        this.user = user;
    }

    public static UserStatCommand of(String userToken, User user) {
        return UserStatCommand.builder()
                .userToken(userToken)
                .user(user)
                .build();
    }
}
