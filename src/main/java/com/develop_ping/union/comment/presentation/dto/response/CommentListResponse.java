package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentListInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponse {
    private List<CommentDetailResponse> comments;
    private long commentCount;

    @Builder
    private CommentListResponse(List<CommentDetailResponse> comments, long commentCount) {
        this.comments = comments;
        this.commentCount = commentCount;
    }

    public static CommentListResponse from(CommentListInfo listInfo) {
        List<CommentDetailResponse> responseList = listInfo.getComments().stream()
                .map(CommentDetailResponse::from)
                .collect(Collectors.toList());

        return CommentListResponse.builder()
                .comments(responseList)
                .commentCount(listInfo.getCommentCount())
                .build();
    }
}
