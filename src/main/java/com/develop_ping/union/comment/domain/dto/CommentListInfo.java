package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListInfo {
    private List<CommentInfo> comments;

    @Builder
    public CommentListInfo(List<Comment> comments) {
        this.comments = comments.stream()
                .map(CommentInfo::of)
                .collect(Collectors.toList());
    }

    public static CommentListInfo of(List<Comment> comments) {
        return CommentListInfo.builder()
                .comments(comments)
                .build();
    }
}
