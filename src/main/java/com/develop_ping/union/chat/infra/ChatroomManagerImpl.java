package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.ChatroomManager;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.chat.exception.ChatroomNotFoundException;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatroomManagerImpl implements ChatroomManager {
    private final ChatroomRepository chatroomRepository;
    @Override
    @Transactional
    public Chatroom findOrMakeChatroom(User sender, User receiver) {
        log.info("개인 채팅방 조회 : 발신자 ID - {}, 수신자 ID - {}", sender.getId(), receiver.getId());

        Chatroom chatroom = chatroomRepository.findBySenderAndReceiverOrReceiverAndSender(sender, receiver, receiver, sender)
                .orElseGet(() -> {
                    log.info("기존 채팅방이 존재하지 않음. 새로운 채팅방 생성 중...");
                    return chatroomRepository.save(Chatroom.of(sender, receiver));
                });

        log.info("개인 채팅방 조회 또는 생성 완료: 개인 채팅방 ID - {}", chatroom.getId());

        return chatroom;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chatroom> findAllChatroomUserInvolved(User user) {
        log.info("해당 유저가 포함된 모든 개인 채팅방 검색 : user ID - {}", user.getId());
        return chatroomRepository.findBySenderOrReceiver(user, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Chatroom findChatroomById(Long chatroomId) {
        log.info("채팅방 검색 : 채팅방 ID - {}", chatroomId);

        return chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new ChatroomNotFoundException(chatroomId));
    }

    @Override
    @Transactional
    public void deleteChatroom(Chatroom chatroom) {
        log.info("채팅방 삭제 : 채팅방 ID - {}", chatroom.getId());
        chatroomRepository.delete(chatroom);
    }
}
