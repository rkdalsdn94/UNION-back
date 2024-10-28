package com.develop_ping.union.comment.domain;

import com.develop_ping.union.comment.domain.entity.Comment;

public interface CommentManager {
    Comment save(Comment comment);
    Comment findById(Long id);
    void delete(Comment comment);
}
