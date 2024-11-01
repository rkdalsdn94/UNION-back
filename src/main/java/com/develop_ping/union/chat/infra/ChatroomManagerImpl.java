package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.ChatroomManager;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatroomManagerImpl implements ChatroomManager {
    private final ChatroomRepository chatroomRepository;
    @Override
    public Long findOrMakeChatroom(User sender, User receiver) {
        log.info("개인 채팅방 조회 또는 생성 시도: 발신자 ID - {}, 수신자 ID - {}", sender.getId(), receiver.getId());

        Chatroom chatroom = chatroomRepository.findBySenderAndReceiverOrReceiverAndSender(sender, receiver, receiver, sender)
                .orElseGet(() -> {
                    log.info("기존 채팅방이 존재하지 않음. 새로운 채팅방 생성 중...");
                    return Chatroom.of(sender, receiver);
                });

        log.info("개인 채팅방 조회 또는 생성 완료: 개인 채팅방 ID - {}", chatroom.getId());

        return chatroom.getId();
    }

    @Override
    public Chatroom findChatroomTwoUserBothInvolved(User user, User targetUser) {
        log.info("개인 채팅방 조회 시도: user ID - {}, targetUser ID - {}", user.getId(), targetUser.getId());

        return chatroomRepository.findBySenderAndReceiverOrReceiverAndSender(user, targetUser, targetUser, user)
                .orElseGet(() -> {
                    log.info("기존 채팅 내역이 없습니다.");
                    return null;
                });
    }
}
