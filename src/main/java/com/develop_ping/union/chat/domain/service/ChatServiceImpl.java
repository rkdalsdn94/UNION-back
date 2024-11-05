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
import com.develop_ping.union.user.domain.BlockUserManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.UserBlockedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    public List<ChatListInfo> readChatroomList(User user, ChatroomType chatroomType) {
        log.info("채팅방 목록 불러오기: 유저 ID - {}, 채팅방 타입 - {}", user.getId(), chatroomType);

        // 차단된 사용자 토큰 수집
        Set<String> blockingUserTokens = getBlockingUserTokens(user);

        // 사용자 참여 채팅방의 최신 메시지 조회
        List<Chatroom> chatrooms = chatroomManager.findAllChatroomUserInvolved(user);
        List<Chat> chats = chatManager.findLatestChatInAllChatroom(chatrooms, chatroomType);

        // 채팅방 타입에 따라 개인 채팅 목록 또는 모임 채팅 목록 반환
        if (chatroomType == ChatroomType.PRIVATE) {
            return getPrivateChatList(user, chats, blockingUserTokens);
        }

        if (chatroomType == ChatroomType.GATHERING) {
            return getGatheringChatList(chats);
        }

        return Collections.emptyList();
    }

    @Override
    public void deleteChatroom(Long chatroomId) {
        log.info("채팅방 삭제 서비스 시작");
        Chatroom chatroom = chatroomManager.findChatroomById(chatroomId);
        chatroomManager.deleteChatroom(chatroom);
        log.info("채팅방 삭제 완료");
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

    private List<ChatListInfo> getGatheringChatList(List<Chat> chats) {
        return chats.stream()
                .map(chat -> ChatListInfo.of(gatheringManager.findById(chat.getTargetId()), chat))
                .toList();
    }

    private User findAnotherUser(Long chatroomId, User user) {
        Chatroom chatroom = chatroomManager.findChatroomById(chatroomId);

        // 채팅방의 상대방 사용자를 반환
        if (chatroom.getSender().equals(user)) return chatroom.getReceiver();
        else return chatroom.getSender();
    }
}
