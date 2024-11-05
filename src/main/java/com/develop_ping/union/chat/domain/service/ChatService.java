package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.dto.ChatListInfo;
import com.develop_ping.union.chat.domain.dto.MessageInfo;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatService {
    ChatInfo readPrivateChat (String userToken, User user);
    ChatInfo readGatheringChat (User user, Long gatheringId);
    List<ChatListInfo> readChatroomList (User user, ChatroomType chatroomType);
}
