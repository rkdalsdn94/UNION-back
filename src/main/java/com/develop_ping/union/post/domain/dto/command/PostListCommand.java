package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListCommand {
    private User user;
    private PostType postType;
    private Pageable pageable;

    @Builder
    public PostListCommand(User user, PostType postType, Pageable pageable) {
        this.user = user;
        this.postType = postType;
        this.pageable = pageable;
    }

    public static PostListCommand of(User user, PostType postType, Pageable pageable) {
        return PostListCommand.builder()
                .user(user)
                .postType(postType)
                .pageable(pageable)
                .build();
    }
}
