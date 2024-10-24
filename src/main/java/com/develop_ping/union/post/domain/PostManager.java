package com.develop_ping.union.post.domain;

import com.develop_ping.union.post.domain.entity.Post;

public interface PostManager {
    Post saveAndFlush(Post post);
    Post findById(Long id);
    Post save(Post post);
    void delete(Post post);
}
