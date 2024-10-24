package com.develop_ping.union.post.domain.dto.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostWithWriterInfo {
    private PostInfo post;
    private WriterInfo writer;

    @Builder
    public PostWithWriterInfo(PostInfo post, WriterInfo writer) {
        this.post = post;
        this.writer = writer;
    }

    public static PostWithWriterInfo of(PostInfo post, WriterInfo writer) {
        return PostWithWriterInfo.builder()
                .post(post)
                .writer(writer)
                .build();
    }
}
