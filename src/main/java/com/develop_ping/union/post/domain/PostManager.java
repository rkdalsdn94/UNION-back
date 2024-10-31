package com.develop_ping.union.post.domain;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostManager {
    Post saveAndFlush(Post post);
    Post findById(Long id);
    Post save(Post post);
    void delete(Post post);
    Page<Post> findByPostType(PostType postType, Pageable pageable);
}
