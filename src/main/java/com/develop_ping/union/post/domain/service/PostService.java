package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.dto.command.PostCreationCommand;
import com.develop_ping.union.post.domain.dto.command.PostDeleteCommand;
import com.develop_ping.union.post.domain.dto.command.PostUpdateCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;

public interface PostService {
    PostInfo createPost(PostCreationCommand command);
    PostInfo updatePost(PostUpdateCommand command);
    void deletePost(PostDeleteCommand command);
    PostInfo getPost(Long postId);
}
