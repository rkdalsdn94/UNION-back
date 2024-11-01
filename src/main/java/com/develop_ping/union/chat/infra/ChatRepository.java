package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByTargetIdAndChatroomType(Long targetId, ChatroomType chatroomType);
}
