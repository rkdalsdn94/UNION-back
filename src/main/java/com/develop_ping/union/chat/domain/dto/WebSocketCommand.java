package com.develop_ping.union.chat.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WebSocketCommand {
    private final String senderNickname;
    private final String receiverToken;
    private final Long gatheringId;
    private final String content;

    @Builder
    public WebSocketCommand(String senderNickname, String receiverToken, Long gatheringId, String content) {
        this.senderNickname = senderNickname;
        this.receiverToken = receiverToken;
        this.gatheringId = gatheringId;
        this.content = content;
    }
}
