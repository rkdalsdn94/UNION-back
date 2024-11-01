package com.develop_ping.union.chat.presentation.dto.response;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatResponse {
    private String senderName;
    private String senderToken;
    private String senderProfileImage;
    private String content;
    private ZonedDateTime createdAt;

    @Builder
    private ChatResponse(String senderName, String senderToken, String senderProfileImage, String content, ZonedDateTime createdAt) {
        this.senderName = senderName;
        this.senderToken = senderToken;
        this.senderProfileImage = senderProfileImage;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ChatResponse from (ChatInfo info) {
        return ChatResponse.builder()
                .senderName(info.getSenderName())
                .senderToken(info.getSenderToken())
                .senderProfileImage(info.getSenderProfileImage())
                .content(info.getContent())
                .createdAt(info.getCreatedAt())
                .build();
    }
}
