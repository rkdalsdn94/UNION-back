package com.develop_ping.union.post.infra;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByType(PostType type, Pageable pageable);
}
