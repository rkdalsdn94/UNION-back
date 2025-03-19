package com.develop_ping.union.user.domain;

import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface BlockUserManager {
    void blockUser (User blockingUser, User blockedUser);
    void unblockUser (User blockingUser, User blockedUser);
    List<User> findAllBlockedUser(User user);
    List<User> findAllBlockedOrBlockingUser (User user);
    boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);

    void deletedByUserInvolved(User user);
}
