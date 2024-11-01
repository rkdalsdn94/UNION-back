package com.develop_ping.union.post.infra;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByType(PostType type, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Post p JOIN p.comments c WHERE c.user = :user")
    Page<Post> findPostsByUserComments(@Param("user") User user, Pageable pageable);
}
