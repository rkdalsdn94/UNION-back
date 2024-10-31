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
    private CommentListInfo(List<CommentInfo> comments) {
        this.comments = comments;
    }

    public static CommentListInfo of(List<Comment> rootComments) {
        List<CommentInfo> commentInfos = rootComments.stream()
                .map(CommentInfo::of)
                .collect(Collectors.toList());

        return CommentListInfo.builder()
                .comments(commentInfos)
                .build();
    }
}
