package com.develop_ping.union.chat.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;

public class Chatroom extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User receiver;
}
