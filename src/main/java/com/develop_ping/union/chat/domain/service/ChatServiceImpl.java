package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.ChatroomManager;
import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final UserManager userManager;
    private final ChatManager chatManager;
    private final ChatroomManager chatroomManager;
    private final GatheringManager gatheringManager;

    @Override
    public List<ChatInfo> readPrivateChat(User user, String userToken) {
        log.info("개인 채팅 내역 불러오기 서비스 시작");
        User targetUser = userManager.findByToken(userToken);
        Chatroom chatroom = chatroomManager.findChatroomTwoUserBothInvolved(user, targetUser);

        if (chatroom == null) {
            log.info("이전 개인 채팅 내역이 없습니다.");
            return List.of();
        }

        List<Chat> chats= chatManager.findChatByTargetIdAndChatroomType(chatroom.getId(), ChatroomType.PRIVATE);
        log.info("개인 채팅 내역을 찾았습니다.");

        return chats.stream()
                .map(ChatInfo::from)
                .toList();
    }

    @Override
    public List<ChatInfo> readGatheringChat(User user, Long gatheringId) {
        log.info("모임 채팅 내역 불러오기 서비스 시작");
        gatheringManager.findById(gatheringId); // 모임이 없으면 예외를 터트리기 위해 사용
        List<Chat> chats = chatManager.findChatByTargetIdAndChatroomType(gatheringId, ChatroomType.GATHERING);
        log.info("모임 채팅 내역을 찾았습니다.");

        return chats.stream()
                .map(ChatInfo::from)
                .toList();
    }
}
