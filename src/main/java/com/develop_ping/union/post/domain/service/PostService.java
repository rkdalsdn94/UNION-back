package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.command.PostListCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.dto.info.PostListInfo;
import com.develop_ping.union.post.domain.dto.info.PostReactionInfo;
import org.springframework.data.domain.Page;

public interface PostService {
    PostInfo createPost(PostCommand command);
    PostInfo updatePost(PostCommand command);
    void deletePost(PostCommand command);
    PostInfo getPost(PostCommand command);
    Page<PostListInfo> getPosts(PostListCommand command);
    boolean likePost(PostCommand command);
    PostReactionInfo getPostLikes(PostCommand command);
}
