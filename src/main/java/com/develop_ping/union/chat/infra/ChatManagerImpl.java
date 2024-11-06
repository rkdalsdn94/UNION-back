package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatManagerImpl implements ChatManager {
    private final ChatRepository chatRepository;

    @Override
    @Transactional
    public Chat save(Chat chat) {
        log.info("채팅 생성 : 사용자 ID - {}, 채팅방 ID - {}", chat.getUser().getId(), chat.getTargetId());
        return chatRepository.save(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findChatByTargetIdAndChatroomType(Long targetId, ChatroomType chatroomType) {
        log.info("채팅 내역 조회 : 채팅방 타입 - {}, 채팅방 ID - {}", chatroomType, targetId);
        return chatRepository.findByTargetIdAndChatroomType(targetId, chatroomType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findLatestChatInAllChatroom(List<Chatroom> chatrooms, ChatroomType chatroomType) {
        log.info("각 채팅방에서 마지막 채팅을 조회");
        List<Chat> latestChats = new ArrayList<>();

        for (Chatroom chatroom : chatrooms) {
            chatRepository.findTopByTargetIdAndChatroomTypeOrderByCreatedAtDesc(chatroom.getId(), chatroomType)
                    .ifPresent(latestChats::add);
        }

        return latestChats;
    }

    @Override
    @Transactional
    public void addUserEnterMessage(Long gatheringId, User user) {
        log.info("유저가 모임 채팅방에 입장 : 유저 ID - {}, 모임 ID - {}", user.getId(), gatheringId);
        Chat chat = Chat.ofSystem(gatheringId, user.getNickname() + "님이 채팅방에 입장하셨습니다.");
        chatRepository.save(chat);
    }

    @Override
    @Transactional
    public void addUserExitMessage(Long gatheringId, User user) {
        log.info("유저가 모임 채팅방을 나감 : 유저 ID - {}, 모임 ID - {}", user.getId(), gatheringId);
        Chat chat = Chat.ofSystem(gatheringId, user.getNickname() + "님이 채팅방에서 나가셨습니다.");
        chatRepository.save(chat);
    }
}
