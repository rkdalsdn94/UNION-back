package com.develop_ping.union.comment.domain;

import com.develop_ping.union.comment.domain.entity.Comment;

import java.util.List;

public interface CommentManager {
    Comment save(Comment comment);
    Comment findById(Long id);
    void delete(Comment comment);
    List<Comment> findByPostIdAndParentIsNull(Long postId);
    List<Comment> findByPostId(Long postId);
    long countByPostId(Long postId);
}
