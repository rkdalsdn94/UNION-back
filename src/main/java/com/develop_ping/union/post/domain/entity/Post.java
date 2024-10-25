package com.develop_ping.union.post.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.post.domain.dto.command.PostCreationCommand;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Post extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private PostType type;

    @Column(nullable = true)
    private String thumbnail;

    @Column(nullable = false)
    private Integer views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Post(String title,
                 String content,
                 PostType type,
                 String thumbnail,
                 Integer views,
                 User user) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.thumbnail = thumbnail;
        this.views = views;
        this.user = user;
    }

    public static Post of(PostCreationCommand command, User user, String thumbnail) {
        return Post.builder()
                .title(command.getTitle())
                .content(command.getContent())
                .type(command.getPostType())
                .thumbnail(thumbnail)
                .views(0)
                .user(user)
                .build();
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void incrementViews() {
        this.views += 1;
    }
}
