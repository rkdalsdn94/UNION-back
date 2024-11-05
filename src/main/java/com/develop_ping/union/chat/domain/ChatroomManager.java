package com.develop_ping.union.chat.domain;

import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatroomManager {

    /**
     * 특정 사용자 간의 채팅방을 조회하거나, 없을 경우 새로 생성합니다.
     * @param sender 채팅방을 요청하는 사용자
     * @param receiver 채팅방의 상대방 사용자
     * @return 조회되거나 새로 생성된 Chatroom 객체
     */
    Chatroom findOrMakeChatroom(User sender, User receiver);

    /**
     * 특정 사용자가 참여 중인 모든 채팅방을 조회합니다.
     * @param user 채팅방 목록을 조회할 사용자
     * @return 사용자가 참여 중인 모든 Chatroom 객체 리스트
     */
    List<Chatroom> findAllChatroomUserInvolved(User user);

    /**
     * 채팅방 ID를 기준으로 특정 채팅방을 조회합니다.
     * @param chatroomId 조회할 채팅방의 ID
     * @return 조회된 Chatroom 객체
     */
    Chatroom findChatroomById(Long chatroomId);

    /**
     * 채팅방을 삭제합니다.
     * @param chatroom 채팅방 엔티티
     */
    void deleteChatroom(Chatroom chatroom);
}

