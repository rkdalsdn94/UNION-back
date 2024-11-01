package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.dto.WebSocketInfo;

public interface WebSocketService {
    WebSocketInfo sendPrivateMessage(WebSocketCommand command);
    WebSocketInfo sendGatheringMessage(WebSocketCommand command);
}
