package com.develop_ping.union.chat.presentation.dto.request;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateChatRequest {
    private String receiverToken;
    private String content;
    private String senderNickname;

    public WebSocketCommand toCommand() {
        return WebSocketCommand.builder()
                .receiverToken(receiverToken)
                .content(content)
                .senderNickname(senderNickname)
                .build();
    }
}
