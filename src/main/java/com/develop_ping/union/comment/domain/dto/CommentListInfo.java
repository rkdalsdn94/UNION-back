package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentListInfo {
    private final List<CommentInfo> comments;
    private final long commentCount;

    @Builder
    private CommentListInfo(List<CommentInfo> comments, long commentCount) {
        this.comments = comments;
        this.commentCount = commentCount;
    }

    public static CommentListInfo of(List<Comment> rootComments, long commentCount) {
        List<CommentInfo> commentInfos = rootComments.stream()
                .map(CommentInfo::of)
                .collect(Collectors.toList());

        return CommentListInfo.builder()
                .comments(commentInfos)
                .commentCount(commentCount)
                .build();
    }
}
