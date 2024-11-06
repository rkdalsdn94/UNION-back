package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.dto.*;

import java.util.List;

public interface CommentService {
    CommentInfo createComment(CommentCommand command);
//    CommentInfo getComment(Long commentId);
    CommentInfo updateComment(CommentCommand command);
    void deleteComment(CommentCommand command);
    List<CommentInfo> getCommentsByPostId(CommentCommand command);
    boolean likeComment(CommentCommand command);
    CommentInfo getBestComment(CommentCommand command);

}
