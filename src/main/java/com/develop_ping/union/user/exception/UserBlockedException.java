package com.develop_ping.union.user.exception;

import lombok.Getter;

@Getter
public class UserBlockedException extends RuntimeException{
    private final String blockingUserName;
    private final String blockedUserName;

    public UserBlockedException(String blockingUserName, String blockedUserName) {
        this.blockingUserName = blockingUserName;
        this.blockedUserName = blockedUserName;
    }
}
