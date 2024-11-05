package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

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
    private final List<CommentInfo> children;

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
                        String univName,
                        List<CommentInfo> children) {
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
        this.children = children;
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

        // 모든 댓글에 대해 자식 리스트를 설정
        List<Comment> childComments = comment.getChildren();
        builder.children(childComments.isEmpty() ? List.of() :
                childComments.stream().map(CommentInfo::of).toList());

        return builder.build();
    }
}
