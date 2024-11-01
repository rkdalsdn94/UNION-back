package com.develop_ping.union.chat.presentation;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.service.ChatService;
import com.develop_ping.union.chat.presentation.dto.response.ChatResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/private/{userToken}")
    public ResponseEntity<List<ChatResponse>> readPrivateChat (@AuthenticationPrincipal User user, @PathVariable String userToken) {
        List<ChatInfo> chatInfos = chatService.readPrivateChat(user, userToken);
        List<ChatResponse> chatResponses = chatInfos.stream()
                .map(ChatResponse::from)
                .toList();

        return ResponseEntity.ok(chatResponses);
    }
}
