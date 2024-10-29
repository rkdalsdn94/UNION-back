package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.CommentManager;
import com.develop_ping.union.comment.domain.dto.CommentCommand;
import com.develop_ping.union.comment.domain.dto.CommentInfo;
import com.develop_ping.union.comment.domain.dto.CommentListInfo;
import com.develop_ping.union.comment.domain.entity.Comment;
import com.develop_ping.union.comment.exception.CommentPermissionDeniedException;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentManager commentManager;
    private final PostManager postManager;

    @Override
    public CommentInfo createComment(CommentCommand command) {
        log.info("[ CommentService.createComment() ] user id: {}", command.getUser().getId());

        User user = command.getUser();
        Post post = postManager.findById(command.getPostId());

        Comment parent = null;
        if (command.getParentId() != null) {
            parent = commentManager.findById(command.getParentId());
        }

        Comment comment = commentManager.save(Comment.of(command.getContent(), post, user, parent));

        return CommentInfo.of(comment);
    }

    @Override
    public CommentInfo getComment(Long commentId) {
        log.info("[ CommentService.getComment() ] comment id: {}", commentId);

        Comment comment = commentManager.findById(commentId);

        return CommentInfo.of(comment);
    }

    @Override
    public CommentInfo updateComment(CommentCommand command) {
        log.info("[ CommentService.updateComment() ] comment id: {}", command.getId());

        Comment comment = commentManager.findById(command.getId());
        User user = command.getUser();

        validateCommentOwner(user, comment);

        comment.updateContent(command.getContent());

        Comment updatedComment = commentManager.save(comment);
        return CommentInfo.of(updatedComment);
    }

    @Override
    public void deleteComment(CommentCommand command) {
        log.info("[ CommentService.deleteComment() ] comment id: {}", command.getId());

        Comment comment = commentManager.findById(command.getId());
        User user = command.getUser();

        validateCommentOwner(user, comment);
        commentManager.delete(comment);
    }

    @Override
    public CommentListInfo getCommentsByPostId(Long postId) {
        log.info("[ CommentService.getCommentsByPostId() ] post id: {}", postId);

        List<Comment> comments = commentManager.findByPostId(postId);

        return CommentListInfo.of(comments);
    }

    private void validateCommentOwner(User user, Comment comment) {
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new CommentPermissionDeniedException(user.getId(), comment.getId());
        }
    }
}
