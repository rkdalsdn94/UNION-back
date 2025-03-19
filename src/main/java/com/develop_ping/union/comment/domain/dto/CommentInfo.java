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
    private final long commentLikes;
    private final boolean liked;
    private final String token;
    private final String nickname;
    private final String profileImage;
    private final String univName;
    private final boolean deleted;

    @Builder
    private CommentInfo(Long id,
                        String content,
                        Long postId,
                        Long parentId,
                        String parentNickname,
                        ZonedDateTime createdAt,
                        long commentLikes,
                        boolean liked,
                        String token,
                        String nickname,
                        String profileImage,
                        String univName,
                        boolean deleted) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.parentNickname = parentNickname;
        this.createdAt = createdAt;
        this.commentLikes = commentLikes;
        this.liked = liked;
        this.token = token;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
        this.deleted = deleted;
    }

    public static CommentInfo from(Comment comment) {
        return CommentInfo.builder()
                .id(comment.getId())
                .build();
    }

    public static CommentInfo of(Comment comment, long commentLikes, boolean liked) {
        if (comment == null) { return null; }

        CommentInfo.CommentInfoBuilder builder = CommentInfo.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .parentNickname(comment.getParentNickname() != null ? comment.getParentNickname() : null)
                .commentLikes(commentLikes)
                .liked(liked)
                .createdAt(comment.getCreatedAt())
                .token(comment.getUser().getToken())
                .nickname(comment.getUser().getNickname())
                .profileImage(comment.getUser().getProfileImage())
                .univName(comment.getUser().getUnivName())
                .deleted(comment.isDeleted());

        return builder.build();
    }

    public static CommentInfo blockedFrom(Comment comment) {
        return CommentInfo.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .parentNickname(comment.getParentNickname() != null ? comment.getParentNickname() : null)
                .commentLikes(0)
                .liked(false)
                .createdAt(comment.getCreatedAt())
                .token(comment.getUser().getToken())
                .nickname(comment.getUser().getNickname())
                .profileImage(comment.getUser().getProfileImage())
                .univName(comment.getUser().getUnivName())
                .deleted(true)
                .build();
    }
}
