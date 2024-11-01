package com.develop_ping.union.chat.presentation;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.service.WebSocketService;
import com.develop_ping.union.chat.presentation.dto.request.GatheringChatRequest;
import com.develop_ping.union.chat.presentation.dto.request.PrivateChatRequest;
import com.develop_ping.union.chat.presentation.dto.response.ChatResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final WebSocketService webSocketService;
    @MessageMapping("/private")
    public void receivePrivateMessage (@Valid PrivateChatRequest request) {
        log.info("개인 메시지 수신: 사용자 이름 - {}", request.getSenderNickname());

        ChatInfo chatInfo = webSocketService.sendPrivateMessage(request.toCommand());
        ChatResponse chatResponse = ChatResponse.from(chatInfo);

        log.info("메시지 전송 준비 완료: 보낸 사람 - {}", chatResponse.getSenderName());
        messagingTemplate.convertAndSend("/topic/private" + chatInfo.getTargetId(), chatResponse);
        log.info("메시지 전송 완료: 경로 - /topic/private{}", chatInfo.getTargetId());
    }

    @MessageMapping("/gathering")
    public void receiveGatheringMessage (@Valid GatheringChatRequest request) {
        log.info("모임 메시지 수신: 사용자 이름 - {}", request.getSenderNickname());

        ChatInfo chatInfo = webSocketService.sendGatheringMessage(request.toCommand());
        ChatResponse chatResponse = ChatResponse.from(chatInfo);

        log.info("메시지 전송 준비 완료: 보낸 사람 - {}", chatResponse.getSenderName());
        messagingTemplate.convertAndSend("/topic/private/" + chatInfo.getTargetId(), chatResponse);
        log.info("메시지 전송 완료: 경로 - /topic/private/{}", chatInfo.getTargetId());
    }
}
