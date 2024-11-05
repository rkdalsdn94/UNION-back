package com.develop_ping.union.chat.domain;

import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatManager {

    /**
     * 새로운 채팅 메시지를 저장합니다.
     * @param chat 저장할 Chat 객체
     * @return 저장된 Chat 객체
     */
    Chat save(Chat chat);

    /**
     * 특정 대상 ID와 채팅방 타입을 기준으로 채팅 메시지를 조회합니다.
     * @param targetId 조회할 대상의 ID (예: 채팅방 또는 모임의 ID)
     * @param chatroomType 조회할 채팅방의 타입 (개인 또는 모임)
     * @return 조회된 Chat 객체 리스트
     */
    List<Chat> findChatByTargetIdAndChatroomType(Long targetId, ChatroomType chatroomType);

    /**
     * 주어진 채팅방 목록에서 각 채팅방의 최신 채팅 메시지를 조회합니다.
     * @param chatrooms 조회할 채팅방 리스트
     * @param chatroomType 조회할 채팅방의 타입 (개인 또는 모임)
     * @return 각 채팅방의 최신 Chat 객체 리스트
     */
    List<Chat> findLatestChatInAllChatroom(List<Chatroom> chatrooms, ChatroomType chatroomType);

    /**
     * 특정 모임 채팅방에 사용자가 들어온 메시지를 추가합니다.
     * @param gatheringId 메시지를 추가할 모임의 ID
     * @param user 들어온 사용자
     */
    void addUserEnterMessage(Long gatheringId, User user);

    /**
     * 특정 모임 채팅방에 사용자가 나간 메시지를 추가합니다.
     * @param gatheringId 메시지를 추가할 모임의 ID
     * @param user 나간 사용자
     */
    void addUserExitMessage(Long gatheringId, User user);
}

