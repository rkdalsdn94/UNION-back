package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatService {
    List<ChatInfo> readPrivateChat (User user, String userToken);
    List<ChatInfo> readGatheringChat (User user, Long gatheringId);
}
