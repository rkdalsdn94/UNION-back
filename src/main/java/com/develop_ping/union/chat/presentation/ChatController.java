package com.develop_ping.union.chat.presentation;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.dto.ChatListInfo;
import com.develop_ping.union.chat.domain.dto.MessageInfo;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.chat.domain.service.ChatService;
import com.develop_ping.union.chat.presentation.dto.response.ChatListResponse;
import com.develop_ping.union.chat.presentation.dto.response.ChatResponse;
import com.develop_ping.union.chat.presentation.dto.response.MessageResponse;
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
    public ResponseEntity<ChatResponse> readPrivateChat (@AuthenticationPrincipal User user,
                                                                  @PathVariable String userToken) {
        log.info("개인 채팅 내역 조회 확인");

        ChatInfo chatInfo = chatService.readPrivateChat(userToken, user);
        ChatResponse chatResponse = ChatResponse.from(chatInfo);

        log.info("개인 채팅 내역 조회 완료");
        return ResponseEntity.ok(chatResponse);
    }

    @GetMapping("/gathering/{gatheringId}")
    public ResponseEntity<ChatResponse> readGatheringChat (@AuthenticationPrincipal User user,
                                                                    @PathVariable Long gatheringId) {
        log.info("모임 채팅 내역 조회 확인");

        ChatInfo chatInfo = chatService.readGatheringChat(user, gatheringId);
        ChatResponse chatResponse = ChatResponse.from(chatInfo);

        log.info("모임 채팅 내역 조회 완료");
        return ResponseEntity.ok(chatResponse);
    }

    @GetMapping("/private")
    public ResponseEntity<List<ChatListResponse>> readPrivateChatroom (@AuthenticationPrincipal User user){
        log.info("개인 채팅방 목록 불러오기 확인");

        List<ChatListInfo> chatListInfos = chatService.readChatroomList(user, ChatroomType.PRIVATE);
        List<ChatListResponse> chatListResponses = chatListInfos.stream()
                .map(ChatListResponse::from)
                .toList();

        log.info("개인 채팅방 목록 조회 완료");
        return ResponseEntity.ok(chatListResponses);
    }

    @GetMapping("/gathering")
    public ResponseEntity<List<ChatListResponse>> readGatheringChatroom (@AuthenticationPrincipal User user){
        log.info("모임 채팅방 목록 불러오기 확인");

        List<ChatListInfo> chatListInfos = chatService.readChatroomList(user, ChatroomType.GATHERING);
        List<ChatListResponse> chatListResponses = chatListInfos.stream()
                .map(ChatListResponse::from)
                .toList();

        log.info("모임 채팅방 목록 조회 완료");
        return ResponseEntity.ok(chatListResponses);
    }
}
