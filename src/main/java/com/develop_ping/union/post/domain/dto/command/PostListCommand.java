package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.post.domain.Criterion;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListCommand {
    private User user;
    private PostType postType;
    private int page;
    private int size;
    private String userToken;
    private Criterion criterion;

    @Builder
    public PostListCommand(User user, PostType postType, int page, int size, String userToken, Criterion criterion) {
        this.user = user;
        this.postType = postType;
        this.page = page;
        this.size = size;
        this.userToken = userToken;
        this.criterion = criterion;
    }

    public static PostListCommand boardOf(User user, PostType postType, int page, int size, Criterion criterion) {
        return PostListCommand.builder()
                .user(user)
                .postType(postType)
                .page(page)
                .size(size)
                .criterion(criterion)
                .build();
    }

    public static PostListCommand myOf(User user, int page, int size, Criterion criterion) {
        return PostListCommand.builder()
                .user(user)
                .page(page)
                .size(size)
                .criterion(criterion)
                .build();
    }

    public static PostListCommand userOf(int page, int size, String userToken, Criterion criterion) {
        return PostListCommand.builder()
                .page(page)
                .size(size)
                .userToken(userToken)
                .criterion(criterion)
                .build();
    }
}
