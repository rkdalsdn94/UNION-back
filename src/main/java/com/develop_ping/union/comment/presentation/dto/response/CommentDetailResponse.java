package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDetailResponse {
    private CommentResponse comment;
    private CommenterResponse commenter;

    @Builder
    public CommentDetailResponse(CommentResponse comment, CommenterResponse commenter) {
        this.comment = comment;
        this.commenter = commenter;
    }

    public static CommentDetailResponse from(CommentInfo info) {
        return CommentDetailResponse.builder()
                .comment(CommentResponse.from(info))
                .commenter(CommenterResponse.from(info))
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CommentResponse {
        private Long id;
        private String content;
        private Long postId;
        private Long parentId;
        private ZonedDateTime createdAt;

        @Builder
        private CommentResponse(Long id,
                               String content,
                               Long postId,
                               Long parentId,
                               ZonedDateTime createdAt) {
            this.id = id;
            this.content = content;
            this.postId = postId;
            this.parentId = parentId;
            this.createdAt = createdAt;
        }

        public static CommentResponse from(CommentInfo info) {
            return CommentResponse.builder()
                    .id(info.getId())
                    .content(info.getContent())
                    .postId(info.getPostId())
                    .parentId(info.getParentId())
                    .createdAt(info.getCreatedAt())
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CommenterResponse {
        private String nickname;
        private String profileImage;
        private String univName;

        @Builder
        private CommenterResponse(String nickname,
                                 String profileImage,
                                 String univName) {
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.univName = univName;
        }

        public static CommenterResponse from(CommentInfo info) {
            return CommenterResponse.builder()
                    .nickname(info.getNickname())
                    .profileImage(info.getProfileImage())
                    .univName(info.getUnivName())
                    .build();
        }
    }
}
