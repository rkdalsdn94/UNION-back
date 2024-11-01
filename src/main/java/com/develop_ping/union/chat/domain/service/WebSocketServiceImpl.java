package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.ChatroomManager;
import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.dto.WebSocketInfo;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketServiceImpl implements WebSocketService{
    private final ChatroomManager chatroomManager;
    private final UserManager userManager;
    private final ChatManager chatManager;

    @Override
    public WebSocketInfo sendPrivateMessage(WebSocketCommand command) {
        log.info("개인 메시지 전송 요청 서비스 시작");
        User receiver = userManager.findByToken(command.getReceiverToken());
        User sender = userManager.findByNickname(command.getSenderNickname());
        Long chatroomId = chatroomManager.findOrMakeChatroom(sender, receiver);
        Chat chat = chatManager.createPrivateChat(sender, chatroomId, command.getContent());
        log.info("개인 메시지 전송 요청 서비스 완료");

        return WebSocketInfo.from(chat);
    }

    @Override
    public WebSocketInfo sendGatheringMessage(WebSocketCommand command) {
        log.info("모임 메시지 전송 요청 서비스 시작");
        User sender = userManager.findByNickname(command.getSenderNickname());
        Chat chat = chatManager.createGatheringChat(sender, command.getGatheringId(), command.getContent());
        log.info("모임 메시지 전송 요청 서비스 완료");

        return WebSocketInfo.from(chat);
    }
}
