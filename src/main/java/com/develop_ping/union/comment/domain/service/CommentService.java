package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.dto.*;

public interface CommentService {
    CommentInfo createComment(CommentCommand command);
    CommentInfo getComment(Long commentId);
    CommentInfo updateComment(CommentCommand command);
    void deleteComment(CommentCommand command);
    CommentListInfo getCommentsByPostId(CommentCommand command);
    boolean likeComment(CommentCommand command);

}
