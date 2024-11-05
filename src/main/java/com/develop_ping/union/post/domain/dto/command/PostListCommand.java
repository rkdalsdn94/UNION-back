package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.post.domain.Criterion;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostListCommand {
    private final User user;
    private final PostType postType;
    private final int page;
    private final int size;
    private final String userToken;
    private final Criterion criterion;
    private final String keyword;

    @Builder
    public PostListCommand(User user,
                           PostType postType,
                           int page,
                           int size,
                           String userToken,
                           Criterion criterion,
                           String keyword) {
        this.user = user;
        this.postType = postType;
        this.page = page;
        this.size = size;
        this.userToken = userToken;
        this.criterion = criterion;
        this.keyword = keyword;
    }

    public static PostListCommand boardOf(User user,
                                          PostType postType,
                                          int page,
                                          int size,
                                          Criterion criterion) {
        return PostListCommand.builder()
                .user(user)
                .postType(postType)
                .page(page)
                .size(size)
                .criterion(criterion)
                .build();
    }

    public static PostListCommand myOf(User user,
                                       int page,
                                       int size,
                                       Criterion criterion) {
        return PostListCommand.builder()
                .user(user)
                .page(page)
                .size(size)
                .criterion(criterion)
                .build();
    }

    public static PostListCommand userOf(int page,
                                         int size,
                                         String userToken,
                                         Criterion criterion) {
        return PostListCommand.builder()
                .page(page)
                .size(size)
                .userToken(userToken)
                .criterion(criterion)
                .build();
    }

    public static PostListCommand searchOf(User user,
                                           PostType type,
                                           String keyword,
                                           int page,
                                           int size,
                                           Criterion criterion) {
        return PostListCommand.builder()
                .user(user)
                .postType(type)
                .keyword(keyword)
                .page(page)
                .size(size)
                .criterion(criterion)
                .build();
    }
}
