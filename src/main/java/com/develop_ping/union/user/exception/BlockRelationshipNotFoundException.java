package com.develop_ping.union.user.exception;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Getter;

@Getter
public class BlockRelationshipNotFoundException extends RuntimeException{
    private final String blockingUserName;
    private final String blockedUserName;

    public BlockRelationshipNotFoundException(String blockingUserName, String blockedUserName) {
        this.blockingUserName = blockingUserName;
        this.blockedUserName = blockedUserName;
    }
}
