package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;

public interface PostService {
    PostInfo createPost(PostCommand command);
    PostInfo updatePost(PostCommand command);
    void deletePost(PostCommand command);
    PostInfo getPost(PostCommand command);
}
