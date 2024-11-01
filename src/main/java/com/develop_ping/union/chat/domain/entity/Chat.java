package com.develop_ping.union.chat.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "chats")
public class Chat extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private ChatroomType chatroomType;

    @Builder
    private Chat(User user, Long targetId, ChatroomType chatroomType, String content) {
        this.user = user;
        this.targetId = targetId;
        this.chatroomType = chatroomType;
        this.content = content;
    }

    public static Chat of (User user, Long targetId, ChatroomType chatroomType, String content) {
        return Chat.builder().
                user(user)
                .targetId(targetId)
                .chatroomType(chatroomType)
                .content(content)
                .build();
    }
}
