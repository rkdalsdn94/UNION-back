package com.develop_ping.union.post.domain;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

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

    // TODO: 작성자 추가하기
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Builder
    public Post(String title, String content, PostType type, String thumbnail) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.thumbnail = thumbnail;
    }
}
