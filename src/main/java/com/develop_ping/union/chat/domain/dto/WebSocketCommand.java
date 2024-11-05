package com.develop_ping.union.chat.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WebSocketCommand {
    private final String senderNickname;
    private final Long chatroomId;
    private final String content;

    @Builder
    public WebSocketCommand(String senderNickname, Long chatroomId, String content) {
        this.senderNickname = senderNickname;
        this.chatroomId = chatroomId;
        this.content = content;
    }
}
