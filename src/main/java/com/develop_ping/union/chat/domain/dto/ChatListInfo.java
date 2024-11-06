package com.develop_ping.union.chat.domain.dto;

import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ChatListInfo {
    private final String title;
    private final Long chatroomId;
    private final ChatroomType chatroomType;
    private final String content;
    private final String userToken;
    private final String profileImage;
    private final ZonedDateTime createdAt;

    @Builder
    private ChatListInfo(String title, Long chatroomId, ChatroomType chatroomType, String content, String userToken, String profileImage, ZonedDateTime createdAt) {
        this.title = title;
        this.chatroomId = chatroomId;
        this.chatroomType = chatroomType;
        this.content = content;
        this.userToken = userToken;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
    }

    public static ChatListInfo of (User user, Chat chat) {
        return ChatListInfo.builder()
                .title(user.getNickname())
                .userToken(user.getToken())
                .profileImage(user.getProfileImage())
                .chatroomId(chat.getTargetId())
                .chatroomType(chat.getChatroomType())
                .content(chat.getContent())
                .createdAt(chat.getCreatedAt())
                .build();
    }

    public static ChatListInfo of (Gathering gathering, Chat chat) {
        return ChatListInfo.builder()
                .title(gathering.getTitle())
                .profileImage(gathering.getThumbnail())
                .chatroomId(chat.getTargetId())
                .chatroomType(chat.getChatroomType())
                .content(chat.getContent())
                .createdAt(chat.getCreatedAt())
                .build();
    }

    public static ChatListInfo ofNoChat (Gathering gathering) {
        return ChatListInfo.builder()
                .title(gathering.getTitle())
                .profileImage(gathering.getThumbnail())
                .chatroomId(gathering.getId())
                .chatroomType(ChatroomType.GATHERING)
                .content("")
                .createdAt(null)
                .build();
    }
}
