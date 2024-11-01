package com.develop_ping.union.chat.presentation;

import com.develop_ping.union.chat.domain.dto.WebSocketInfo;
import com.develop_ping.union.chat.domain.service.WebSocketService;
import com.develop_ping.union.chat.presentation.dto.request.PrivateChatRequest;
import com.develop_ping.union.chat.presentation.dto.response.ChatResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final WebSocketService webSocketService;
    @MessageMapping("/private")
    public void receivePrivateMessage (PrivateChatRequest request) {
        log.info("개인 메시지 수신: 사용자 이름 - {}", request.getSenderNickname());

        WebSocketInfo webSocketInfo = webSocketService.sendPrivateMessage(request.toCommand());
        ChatResponse chatResponse = ChatResponse.from(webSocketInfo);

        log.info("메시지 전송 준비 완료: 보낸 사람 - {}", chatResponse.getSenderName());
        messagingTemplate.convertAndSend("/topic/private" + webSocketInfo.getTargetId(), chatResponse);
        log.info("메시지 전송 완료: 경로 - /topic/private{}", webSocketInfo.getTargetId());
    }
}
