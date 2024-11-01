package com.develop_ping.union.chat.presentation.dto.request;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GatheringChatRequest {
    @NotNull
    private Long gatheringId;
    @NotNull
    private String content;
    @NotNull
    private String senderNickname;

    public WebSocketCommand toCommand() {
        return WebSocketCommand.builder()
                .gatheringId(gatheringId)
                .content(content)
                .senderNickname(senderNickname)
                .build();
    }
}
