package com.develop_ping.union.chat.infra;

import com.develop_ping.union.chat.domain.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

}
