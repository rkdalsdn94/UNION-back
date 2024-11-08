package com.develop_ping.union.comment.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    @JsonIgnore // 순환 참조 방지
    private Comment parent;

    @Column(nullable = true)
    private String parentNickname;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("createdAt ASC")
    private List<Comment> children = new ArrayList<>();

    @Column(nullable = false)
    private boolean deleted;

    @Builder
    private Comment(String content,
                    Post post,
                    User user,
                    Comment parent,
                    String parentNickname) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.parent = parent;
        this.parentNickname = parentNickname;
    }

    public static Comment of(String content,
                             Post post,
                             User user,
                             Comment parent,
                             String parentNickname) {
        return Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .parent(parent)
                .parentNickname(parentNickname)
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void softDelete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
