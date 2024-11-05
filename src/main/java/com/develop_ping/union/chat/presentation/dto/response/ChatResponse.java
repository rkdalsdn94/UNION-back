package com.develop_ping.union.chat.presentation.dto.response;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatResponse {
    private String title;
    private Long chatroomId;
    private ChatroomType chatroomType;
    private List<MessageResponse> messageInfoList;

    @Builder
    private ChatResponse(String title, Long chatroomId, ChatroomType chatroomType, List<MessageResponse> messageInfoList) {
        this.title = title;
        this.chatroomId = chatroomId;
        this.chatroomType = chatroomType;
        this.messageInfoList = messageInfoList;
    }

    public static ChatResponse from (ChatInfo chatInfo) {
        List<MessageResponse> messageResponses = chatInfo.getMessageInfoList()
                .stream()
                .map(MessageResponse::from)
                .toList();

        return ChatResponse.builder()
                .title(chatInfo.getTitle())
                .chatroomId(chatInfo.getChatroomId())
                .chatroomType(chatInfo.getChatroomType())
                .messageInfoList(messageResponses)
                .build();
    }
}
