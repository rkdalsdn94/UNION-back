package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentInfo {
    private Long id;
    private String content;
    private Long postId;
    private Long parentId;
    private ZonedDateTime createdAt;
    private String nickname;
    private String profileImage;
    private String univName;

    @Builder
    private CommentInfo(Long id,
                        String content,
                        Long postId,
                        Long parentId,
                        ZonedDateTime createdAt,
                        String nickname,
                        String profileImage,
                        String univName) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
    }

    public static CommentInfo of(Comment comment) {
        return CommentInfo.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .parentId(comment.getParent().getId())
                .createdAt(comment.getCreatedAt())
                .nickname(comment.getUser().getNickname())
                .profileImage(comment.getUser().getProfileImage())
                .univName(comment.getUser().getUnivName())
                .build();
    }
}
