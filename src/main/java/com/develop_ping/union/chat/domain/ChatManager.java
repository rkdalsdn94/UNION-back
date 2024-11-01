package com.develop_ping.union.chat.domain;

import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatManager {
    Chat save (Chat chat);

    List<Chat> findChatByTargetIdAndChatroomType (Long targetId, ChatroomType chatroomType);
}
