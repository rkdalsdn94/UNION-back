package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    // 유저 1이 sender, 유저 2가 receiver이거나 반대인 경우의 채팅방을 찾는 메서드
    @Query("SELECT c FROM Chatroom c WHERE " +
            "(c.sender = :sender1 AND c.receiver = :receiver1) OR " +
            "(c.sender = :sender2 AND c.receiver = :receiver2)")
    Optional<Chatroom> findBySenderAndReceiverOrReceiverAndSender(
            @Param("sender1") User sender1,
            @Param("receiver1") User receiver1,
            @Param("sender2") User sender2,
            @Param("receiver2") User receiver2);

    // 특정 유저가 sender나 receiver로 포함된 모든 채팅방 찾기
    List<Chatroom> findBySenderOrReceiver(User user1, User user2);
}
