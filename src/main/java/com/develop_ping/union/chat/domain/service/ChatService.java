package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.dto.ChatListInfo;
import com.develop_ping.union.chat.domain.dto.MessageInfo;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatService {

    /**
     * 특정 사용자와의 개인 채팅 내역을 조회합니다.
     * @param userToken 조회 대상 사용자의 토큰
     * @param user 요청을 보낸 사용자
     * @return 조회된 개인 채팅 내역을 담은 ChatInfo 객체
     */
    ChatInfo readPrivateChat (String userToken, User user);

    /**
     * 특정 모임의 채팅 내역을 조회합니다.
     * @param user 요청을 보낸 사용자
     * @param gatheringId 조회 대상 모임 ID
     * @return 조회된 모임 채팅 내역을 담은 ChatInfo 객체
     */
    ChatInfo readGatheringChat (User user, Long gatheringId);

    /**
     * 사용자가 참여한 모든 개인 채팅방 목록을 조회합니다.
     * @param user 요청을 보낸 사용자
     * @return 조회된 채팅방 목록을 담은 ChatListInfo 리스트
     */
    List<ChatListInfo> readPrivateChatroomList (User user);

    /**
     * 해당 채팅방을 삭제합니다.
     * @param chatroomId 채팅방 ID
     */
    void deleteChatroom (Long chatroomId);

    /**
     * 사용자가 참여한 모든 모임 채팅방 목록을 조회합니다.
     * @param user 요청을 보낸 사용자
     * @return 조회된 채팅방 목록을 담은 ChatListInfo 리스트
     */
    List<ChatListInfo> readGatheringChatroomList (User user);
}
