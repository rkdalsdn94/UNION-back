package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.dto.MessageInfo;

public interface WebSocketService {
    MessageInfo sendPrivateMessage(WebSocketCommand command);
    MessageInfo sendGatheringMessage(WebSocketCommand command);
}
