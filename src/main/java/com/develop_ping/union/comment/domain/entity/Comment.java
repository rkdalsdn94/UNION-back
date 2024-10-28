package com.develop_ping.union.comment.domain.entity;

import com.develop_ping.union.comment.domain.dto.CommentCommand;
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

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY) // 부모 댓글 삭제되어도 유지
    private List<Comment> children = new ArrayList<>();

    @Builder
    private Comment(String content,
                   Post post,
                   User user,
                   Comment parent) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.parent = parent;
    }

    public static Comment of(String content, Post post, User user, Comment parent) {
        return Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .parent(parent)
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void addChildComment(Comment comment) {
        this.children.add(comment);
    }
}
