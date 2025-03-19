package com.develop_ping.union.chat.domain.service;

import com.develop_ping.union.chat.domain.ChatManager;
import com.develop_ping.union.chat.domain.ChatroomManager;
import com.develop_ping.union.chat.domain.dto.ChatInfo;
import com.develop_ping.union.chat.domain.dto.ChatListInfo;
import com.develop_ping.union.chat.domain.dto.MessageInfo;
import com.develop_ping.union.chat.domain.entity.Chat;
import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.chat.domain.entity.ChatroomType;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.user.domain.BlockUserManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.UserBlockedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserManager userManager;
    private final ChatManager chatManager;
    private final ChatroomManager chatroomManager;
    private final GatheringManager gatheringManager;
    private final BlockUserManager blockUserManager;
    private final PartyManager partyManager;

    @Override
    public ChatInfo readPrivateChat(String userToken, User user) {
        log.info("개인 채팅 내역 불러오기 서비스 시작");

        // 대상 사용자 찾기
        User targetUser = userManager.findByToken(userToken);

        // 사용자 간 채팅방 조회 또는 생성
        Chatroom chatroom = chatroomManager.findOrMakeChatroom(user, targetUser);

        // 채팅방 내 모든 메시지 조회
        List<Chat> chats = chatManager.findChatByTargetIdAndChatroomType(chatroom.getId(), ChatroomType.PRIVATE);

        // 메시지 정보 리스트로 변환
        List<MessageInfo> messageInfoList = chats.stream()
                .map(MessageInfo::from)
                .toList();

        // ChatInfo 객체 생성 후 반환
        return ChatInfo.builder()
                .title(targetUser.getNickname())
                .messageInfoList(messageInfoList)
                .chatroomId(chatroom.getId())
                .chatroomType(ChatroomType.PRIVATE)
                .build();
    }


    @Override
    public ChatInfo readGatheringChat(User user, Long gatheringId) {
        log.info("모임 채팅 내역 불러오기 서비스 시작");

        // 모임 정보 조회
        Gathering gathering = gatheringManager.findById(gatheringId);

        // 모임 채팅방 내 모든 메시지 조회
        List<Chat> chats = chatManager.findChatByTargetIdAndChatroomType(gatheringId, ChatroomType.GATHERING);

        // 메시지 정보 리스트로 변환
        List<MessageInfo> messageInfoList = chats.stream()
                .map(MessageInfo::from)
                .toList();

        // ChatInfo 객체 생성 후 반환
        return ChatInfo.builder()
                .title(gathering.getTitle())
                .messageInfoList(messageInfoList)
                .chatroomId(gatheringId)
                .chatroomType(ChatroomType.GATHERING)
                .build();
    }

    @Override
    public List<ChatListInfo> readPrivateChatroomList(User user) {
        log.info("개인 채팅방 목록 불러오기: 유저 ID - {}", user.getId());

        // 차단된 사용자 토큰 수집
        Set<String> blockingUserTokens = getBlockingUserTokens(user);

        // 사용자 참여 채팅방의 최신 메시지 조회
        List<Long> chatrooms = chatroomManager.findAllChatroomUserInvolved(user)
                .stream().map(Chatroom::getId).toList();

        List<Chat> chats = chatManager.findLatestChatInAllChatroom(chatrooms, ChatroomType.PRIVATE);

        return getPrivateChatList(user, chats, blockingUserTokens);
    }

    @Override
    public void deleteChatroom(Long chatroomId) {
        log.info("채팅방 삭제 서비스 시작");
        Chatroom chatroom = chatroomManager.findChatroomById(chatroomId);
        chatroomManager.deleteChatroom(chatroom);
        log.info("채팅방 삭제 완료");
    }

    @Override
    public List<ChatListInfo> readGatheringChatroomList(User user) {
        log.info("모임 채팅방 목록 불러오기: 유저 ID - {}", user.getId());
        List<Party> parties  = partyManager.findByUserId(user.getId());

        List<Gathering> gatherings = parties.stream()
                .map(Party::getGathering)
                .toList();

        List<Long> gatheringIds = gatherings.stream()
                .map(Gathering::getId)
                .toList();

        List<Chat> chats = chatManager.findLatestChatInAllChatroom(gatheringIds, ChatroomType.GATHERING);

        // 최신 Chat을 Gathering ID로 매핑 (chats에 없는 경우 기본값 설정)
        Map<Long, Chat> chatMap = chats.stream()
                .collect(Collectors.toMap(Chat::getTargetId, chat -> chat));

        // Gathering을 순회하며 ChatListInfo 생성
        return gatherings.stream()
                .map(gathering -> {
                    Chat chat = chatMap.get(gathering.getId());
                    if (chat != null) {
                        // 최신 Chat이 있는 경우
                        return ChatListInfo.of(gathering, chat);
                    } else {
                        // 최신 Chat이 없는 경우 기본값 생성
                        return ChatListInfo.ofNoChat(gathering);
                    }
                })
                .toList();
    }

    private Set<String> getBlockingUserTokens(User user) {
        return blockUserManager.findAllBlockedOrBlockingUser(user)
                .stream()
                .map(User::getToken)
                .collect(Collectors.toSet());
    }

    private List<ChatListInfo> getPrivateChatList(User user, List<Chat> chats, Set<String> blockingUserTokens) {
        return chats.stream()
                .map(chat -> {
                    User anotherUser = findAnotherUser(chat.getTargetId(), user);
                    return anotherUser.isDeleted() ? null : ChatListInfo.of(anotherUser, chat);
                })
                .filter(Objects::nonNull) // null 값(논리 삭제된 사용자와의 채팅) 제외
                .filter(chatInfo -> !blockingUserTokens.contains(chatInfo.getUserToken())) // 차단 유저 정보 제외
                .toList();
    }

    private User findAnotherUser(Long chatroomId, User user) {
        Chatroom chatroom = chatroomManager.findChatroomById(chatroomId);

        // 채팅방의 상대방 사용자를 반환
        if (chatroom.getSender().equals(user)) return chatroom.getReceiver();
        else return chatroom.getSender();
    }
}
