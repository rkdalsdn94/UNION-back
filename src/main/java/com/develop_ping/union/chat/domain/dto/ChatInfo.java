package com.develop_ping.union.chat.domain.dto;

import com.develop_ping.union.chat.domain.entity.Chat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ChatInfo {
    private final String content;
    private final String senderName;
    private final String senderProfileImage;
    private final String senderToken;
    private final ZonedDateTime createdAt;
    private final Long targetId;

    @Builder
    private ChatInfo(String content, String senderName, String senderProfileImage, String senderToken, ZonedDateTime createdAt, Long targetId) {
        this.content = content;
        this.senderName = senderName;
        this.senderProfileImage = senderProfileImage;
        this.senderToken = senderToken;
        this.createdAt = createdAt;
        this.targetId = targetId;
    }

    public static ChatInfo from (Chat chat) {
        return ChatInfo.builder()
                .content(chat.getContent())
                .senderName(chat.getUser().getNickname())
                .senderProfileImage(chat.getUser().getProfileImage())
                .senderToken(chat.getUser().getToken())
                .targetId(chat.getTargetId())
                .createdAt(chat.getCreatedAt())
                .build();
    }
}
