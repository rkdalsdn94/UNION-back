package com.develop_ping.union.comment.infra;

import com.develop_ping.union.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndParentIsNull(Long postId);
    long countByPostId(Long postId);
}
