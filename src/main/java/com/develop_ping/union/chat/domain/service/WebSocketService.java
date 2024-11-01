package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.dto.ChatInfo;

public interface WebSocketService {
    ChatInfo sendPrivateMessage(WebSocketCommand command);
    ChatInfo sendGatheringMessage(WebSocketCommand command);
}
