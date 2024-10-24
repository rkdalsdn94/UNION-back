package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.dto.command.*;
import com.develop_ping.union.post.domain.dto.info.*;

public interface PostService {
    PostCreationInfo createPost(PostCreationCommand command);
    PostUpdateInfo updatePost(PostUpdateCommand command);
    void deletePost(String token, Long postId);
}
