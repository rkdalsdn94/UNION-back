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
    // TODO: 기존 로직 다 지우고 가지고 온 최상위 댓글의 자식 댓글들 depth 수정하기
    private List<CommentDetailResponse> comments;

    @Builder
    private CommentListResponse(List<CommentDetailResponse> comments) {
        this.comments = comments;
    }

    public static CommentListResponse from(CommentListInfo listInfo) {
        List<CommentDetailResponse> responseList = listInfo.getComments().stream()
                .map(CommentDetailResponse::from)
                .collect(Collectors.toList());

        return CommentListResponse.builder()
                .comments(responseList)
                .build();
    }
}
