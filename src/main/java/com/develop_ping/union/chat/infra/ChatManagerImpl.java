package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatManagerImpl implements ChatManager {
    private final ChatRepository chatRepository;

    @Override
    public Chat save(Chat chat) {
        log.info("채팅 생성 : 사용자 ID - {}, 채팅방 ID - {}", chat.getUser().getId(), chat.getTargetId());
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findChatByTargetIdAndChatroomType(Long targetId, ChatroomType chatroomType) {
        log.info("채팅 내역 조회 : 채팅방 타입 - {}, 채팅방 ID - {}", chatroomType, targetId);
        return chatRepository.findByTargetIdAndChatroomType(targetId, chatroomType);
    }
}
