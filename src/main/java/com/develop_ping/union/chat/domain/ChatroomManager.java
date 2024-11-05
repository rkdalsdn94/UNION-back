package com.develop_ping.union.chat.domain;

import com.develop_ping.union.chat.domain.entity.Chatroom;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ChatroomManager {
    Chatroom findOrMakeChatroom(User sender, User receiver);
    Chatroom findChatroomTwoUserBothInvolved(User user, User targetUser);
    List<Chatroom> findAllChatroomUserInvolved(User user);

    Chatroom findChatroomById (Long chatroomId);
}
