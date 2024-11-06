package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class CommentInfo {
    private final Long id;
    private final String content;
    private final Long postId;
    private final Long parentId;
    private final String parentNickname;
    private final ZonedDateTime createdAt;
    private final String token;
    private final String nickname;
    private final String profileImage;
    private final String univName;

    @Builder
    private CommentInfo(Long id,
                        String content,
                        Long postId,
                        Long parentId,
                        String parentNickname,
                        ZonedDateTime createdAt,
                        String token,
                        String nickname,
                        String profileImage,
                        String univName) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.parentNickname = parentNickname;
        this.createdAt = createdAt;
        this.token = token;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
    }

    public static CommentInfo of(Comment comment) {
        CommentInfo.CommentInfoBuilder builder = CommentInfo.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .parentNickname(comment.getParentNickname() != null ? comment.getParentNickname() : null)
                .createdAt(comment.getCreatedAt())
                .token(comment.getUser().getToken())
                .nickname(comment.getUser().getNickname())
                .profileImage(comment.getUser().getProfileImage())
                .univName(comment.getUser().getUnivName());

        return builder.build();
    }
}
