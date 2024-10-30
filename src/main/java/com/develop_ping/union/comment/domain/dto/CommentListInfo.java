package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.comment.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListInfo {
    // TODO: 기존 로직 다 지우고 comment list info는 최상위 댓글만 가지고 있게 변경하기
    private List<CommentInfo> comments;

    @Builder
    private CommentListInfo(List<CommentInfo> comments) {
        this.comments = comments;
    }

    public static CommentListInfo of(List<Comment> comments) {
        // 모든 댓글을 CommentInfo 객체로 변환하고 ID를 기준으로 Map에 저장
        Map<Long, CommentInfo> commentInfoMap = comments.stream()
                .map(CommentInfo::of)
                .collect(Collectors.toMap(CommentInfo::getId, info -> info));

        // 각 댓글의 부모를 찾아서 children 리스트에 추가하여 트리 구조를 구성
        List<CommentInfo> topComments = new ArrayList<>();
        for (CommentInfo info : commentInfoMap.values()) {
            if (info.getParentId() == null) {
                topComments.add(info); // 최상위 댓글은 루트 리스트에 추가
            } else {
                CommentInfo parentInfo = commentInfoMap.get(info.getParentId());
                if (parentInfo != null) {
                    parentInfo.getChildren().add(info); // 부모의 children에 info 추가
                }
            }
        }

        return CommentListInfo.builder()
                .comments(topComments)
                .build();
    }
}
