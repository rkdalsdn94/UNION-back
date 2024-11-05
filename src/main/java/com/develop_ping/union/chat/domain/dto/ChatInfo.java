package com.develop_ping.union.chat.domain.dto;

import com.develop_ping.union.chat.domain.entity.ChatroomType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatInfo {
    private final String title;
    private final Long chatroomId;
    private final ChatroomType chatroomType;
    private final List<MessageInfo> messageInfoList;

    @Builder
    public ChatInfo(String title, Long chatroomId, ChatroomType chatroomType, List<MessageInfo> messageInfoList) {
        this.title = title;
        this.chatroomId = chatroomId;
        this.chatroomType = chatroomType;
        this.messageInfoList = messageInfoList;
    }
}
