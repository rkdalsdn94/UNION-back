package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
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

    public static CommentListResponse from(List<CommentInfo> infos) {
        // commentInfo -> CommentDetailResponse
        Map<Long, CommentDetailResponse> responseMap = infos.stream()
                .map(CommentDetailResponse::from)
                .collect(Collectors.toMap(CommentDetailResponse::getId, response -> response));

        // 부모 댓글에 자식 댓글 추가
        responseMap.values().forEach(response -> {
            Long parentId = response.getParentId();
            if (parentId != null) {
                responseMap.get(parentId).getChildren().add(response);
            }
        });

        List<CommentDetailResponse> topLevelComments = responseMap.values().stream()
                .filter(response -> response.getParentId() == null)
                .toList();

        return CommentListResponse.builder()
                .comments(topLevelComments)
                .commentCount(infos.size())
                .build();
    }
}
