package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    // 유저 1이 sender, 유저 2가 receiver이거나 반대인 경우의 채팅방을 찾는 메서드
    Optional<Chatroom> findBySenderAndReceiverOrReceiverAndSender(User sender1, User receiver1, User sender2, User receiver2);

    // 특정 유저가 sender나 receiver로 포함된 모든 채팅방 찾기
    List<Chatroom> findBySenderOrReceiver(User user1, User user2);
}
