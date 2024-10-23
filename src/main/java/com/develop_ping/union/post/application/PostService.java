package com.develop_ping.union.post.application;

import com.develop_ping.union.post.application.dto.command.*;
import com.develop_ping.union.post.application.dto.info.*;

public interface PostService {
    PostCreationInfo createPost(PostCreationCommand command);
    PostUpdateInfo updatePost(PostUpdateCommand command);
    void deletePost(String token, Long postId);
}
