package com.develop_ping.union.chat.presentation.dto.response;

import com.develop_ping.union.chat.domain.dto.ChatListInfo;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatListResponse {
    private String title;
    private Long chatroomId;
    private ChatroomType chatroomType;
    private String content;
    private String userToken;
    private String profileImage;
    private ZonedDateTime createdAt;

    @Builder
    private ChatListResponse(String title, Long chatroomId, ChatroomType chatroomType, String content, String userToken, String profileImage, ZonedDateTime createdAt) {
        this.title = title;
        this.chatroomId = chatroomId;
        this.chatroomType = chatroomType;
        this.content = content;
        this.userToken = userToken;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
    }

    public static ChatListResponse from (ChatListInfo chatListInfo) {
        return ChatListResponse.builder()
                .title(chatListInfo.getTitle())
                .profileImage(chatListInfo.getProfileImage())
                .userToken(chatListInfo.getUserToken())
                .chatroomId(chatListInfo.getChatroomId())
                .chatroomType(chatListInfo.getChatroomType())
                .content(chatListInfo.getContent())
                .createdAt(chatListInfo.getCreatedAt())
                .build();
    }
}
