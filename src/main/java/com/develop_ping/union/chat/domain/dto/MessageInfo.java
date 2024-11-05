package com.develop_ping.union.chat.domain.dto;

import com.develop_ping.union.chat.domain.entity.Chat;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class MessageInfo {
    private final String content;
    private final String senderName;
    private final String senderProfileImage;
    private final String senderToken;
    private final ZonedDateTime createdAt;

    @Builder
    private MessageInfo (String content, String senderName, String senderProfileImage, String senderToken, ZonedDateTime createdAt) {
        this.content = content;
        this.senderName = senderName;
        this.senderProfileImage = senderProfileImage;
        this.senderToken = senderToken;
        this.createdAt = createdAt;
    }

    public static MessageInfo from (Chat chat) {
        return MessageInfo.builder()
                .content(chat.getContent())
                .senderName(chat.getUser() == null ? "System" : chat.getUser().getNickname())
                .senderProfileImage(chat.getUser() == null ? "" : chat.getUser().getProfileImage())
                .senderToken(chat.getUser() == null ? "" : chat.getUser().getToken())
                .createdAt(chat.getCreatedAt())
                .build();
    }
}
