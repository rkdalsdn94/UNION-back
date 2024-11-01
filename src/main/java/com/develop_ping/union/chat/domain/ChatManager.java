package com.develop_ping.union.chat.domain;

import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.user.domain.entity.User;

public interface ChatManager {
    Chat createPrivateChat (User sender, Long chatroomId, String content);
}
