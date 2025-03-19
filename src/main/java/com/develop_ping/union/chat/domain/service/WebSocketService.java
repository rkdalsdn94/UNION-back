package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.dto.MessageInfo;

public interface WebSocketService {

    /**
     * 특정 사용자와의 개인 채팅방에 메시지를 전송합니다.
     * @param command 전송할 메시지의 내용을 포함한 WebSocketCommand 객체
     * @return 전송된 메시지 정보를 담은 MessageInfo 객체
     */
    MessageInfo sendPrivateMessage(WebSocketCommand command);

    /**
     * 특정 모임 채팅방에 메시지를 전송합니다.
     * @param command 전송할 메시지의 내용을 포함한 WebSocketCommand 객체
     * @return 전송된 메시지 정보를 담은 MessageInfo 객체
     */
    MessageInfo sendGatheringMessage(WebSocketCommand command);
}
