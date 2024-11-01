package com.develop_ping.union.chat.domain;

import com.develop_ping.union.user.domain.entity.User;

public interface ChatroomManager {
    Long findOrMakeChatroom (User sender, User receiver);
}
