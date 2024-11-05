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
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final UserManager userManager;
    private final ChatManager chatManager;
    private final ChatroomManager chatroomManager;
    private final GatheringManager gatheringManager;

    @Override
    public ChatInfo readPrivateChat(String userToken, User user) {
        log.info("개인 채팅 내역 불러오기 서비스 시작");

        User targetUser = userManager.findByToken(userToken);
        Chatroom chatroom = chatroomManager.findOrMakeChatroom(user, targetUser);
        List<Chat> chats= chatManager.findChatByTargetIdAndChatroomType(chatroom.getId(), ChatroomType.PRIVATE);

        List<MessageInfo> messageInfoList = chats.stream()
                .map(MessageInfo::from)
                .toList();

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

        Gathering gathering = gatheringManager.findById(gatheringId); // 모임이 없으면 예외를 터트리기 위해 사용
        List<Chat> chats = chatManager.findChatByTargetIdAndChatroomType(gatheringId, ChatroomType.GATHERING);

        List<MessageInfo> messageInfoList = chats.stream()
                .map(MessageInfo::from)
                .toList();

        return ChatInfo.builder()
                .title(gathering.getTitle())
                .messageInfoList(messageInfoList)
                .chatroomId(gatheringId)
                .chatroomType(ChatroomType.GATHERING)
                .build();
    }

    @Override
    public List<ChatListInfo> readChatroomList (User user, ChatroomType chatroomType) {
        log.info("채팅방 목록 불러오기: 유저 ID - {}, 채팅방 타입 - {}", user.getId(), chatroomType);
        List<Chatroom> chatrooms = chatroomManager.findAllChatroomUserInvolved(user);
        List<Chat> chats = chatManager.findLatestChatInAllChatroom(chatrooms, chatroomType);

        if (chatroomType == ChatroomType.PRIVATE) {
            return chats.stream()
                    .map(chat -> ChatListInfo.of(findAnotherUser(chat.getTargetId(), user), chat))
                    .toList();
        }

        if (chatroomType == ChatroomType.GATHERING) {
            return chats.stream()
                    .map((chat -> ChatListInfo.of(gatheringManager.findById(chat.getTargetId()), chat)))
                    .toList();
        }

        return null;
    }

    private User findAnotherUser (Long chatroomId, User user) {
        Chatroom chatroom = chatroomManager.findChatroomById(chatroomId);

        if (chatroom.getSender().equals(user)) return chatroom.getReceiver();
        else return chatroom.getSender();
    }
}
