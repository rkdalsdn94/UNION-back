package com.develop_ping.union.chat.presentation.dto.request;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateChatRequest {
    @NotNull
    private Long chatroomId;
    @NotNull
    private String content;
    @NotNull
    private String senderNickname;

    public WebSocketCommand toCommand() {
        return WebSocketCommand.builder()
                .chatroomId(chatroomId)
                .content(content)
                .senderNickname(senderNickname)
                .build();
    }
}
