package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.dto.WebSocketCommand;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatManagerImpl implements ChatManager {
    private final ChatRepository chatRepository;

    @Override
    public Chat createPrivateChat(User sender, Long chatroomId, String content) {
        log.info("개인 채팅 생성 시도: 사용자 ID - {}, 채팅방 ID - {}", sender.getId(), chatroomId);

        Chat chat = Chat.of(sender, chatroomId, ChatroomType.PRIVATE, content);
        Chat savedChat = chatRepository.save(chat);

        log.info("개인 채팅 생성 완료: 채팅 ID - {}, 사용자 ID - {}, 채팅방 ID - {}", savedChat.getId(), sender.getId(), chatroomId);

        return savedChat;
    }
}
