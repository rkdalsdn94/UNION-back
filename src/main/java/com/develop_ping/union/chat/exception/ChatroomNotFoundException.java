package com.develop_ping.union.chat.exception;

import lombok.Getter;

@Getter
public class ChatroomNotFoundException extends RuntimeException{
    private final Long chatroomId;

    public ChatroomNotFoundException(Long chatroomId) {
        this.chatroomId = chatroomId;
    }
}
