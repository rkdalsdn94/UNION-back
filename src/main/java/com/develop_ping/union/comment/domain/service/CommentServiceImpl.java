package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.CommentManager;
import com.develop_ping.union.comment.domain.dto.*;
import com.develop_ping.union.comment.domain.entity.Comment;
import com.develop_ping.union.comment.exception.CommentPermissionDeniedException;
import com.develop_ping.union.comment.exception.CommenterMismatchException;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentManager commentManager;
    private final PostManager postManager;

    @Override
    @Transactional
    public CommentInfo createComment(CommentCommand command) {
        log.info("[ CommentService.createComment() ] user id: {}", command.getUser().getId());

        User user = command.getUser();
        Post post = postManager.findById(command.getPostId());
        Comment parent = getValidatedParent(command.getParentId(), command.getParentNickname());

        Comment comment = commentManager.save(Comment.of(command.getContent(), post, user, parent, command.getParentNickname()));

        log.info("[ New Comment! ] comment id: {}", comment.getId());
        return CommentInfo.of(comment);
    }

    @Override
    public CommentInfo getComment(Long commentId) {
        log.info("[ CommentService.getComment() ] comment id: {}", commentId);

        Comment comment = commentManager.findById(commentId);

        return CommentInfo.of(comment);
    }

    @Override
    @Transactional
    public CommentInfo updateComment(CommentCommand command) {
        log.info("[ CommentService.updateComment() ] comment id: {}", command.getId());

        Comment comment = commentManager.findById(command.getId());
        User user = command.getUser();

        validateCommentOwner(user, comment);

        comment.updateContent(command.getContent());

        Comment updatedComment = commentManager.save(comment);

        log.info("[ Updated Comment! ] comment id: {}", updatedComment.getId());
        return CommentInfo.of(updatedComment);
    }

    @Override
    @Transactional
    public void deleteComment(CommentCommand command) {
        log.info("[ CommentService.deleteComment() ] comment id: {}", command.getId());

        Comment comment = commentManager.findById(command.getId());
        User user = command.getUser();

        validateCommentOwner(user, comment);
        commentManager.delete(comment);
        log.info("[ Deleted Comment! ] comment id: {}", comment.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentListInfo getCommentsByPostId(Long postId) {
        log.info("[ CommentService.getCommentsByPostId() ] post id: {}", postId);

        Post post = postManager.findById(postId);
        List<Comment> rootComments = commentManager.findByPostIdAndParentIsNull(post.getId());

        log.info("[ Found Comments! ]");
        return CommentListInfo.of(rootComments);
    }

    private void validateCommentOwner(User user, Comment comment) {
        log.info("[ validateCommentOwner() ]");
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new CommentPermissionDeniedException(user.getId(), comment.getId());
        }
    }

    private Comment getValidatedParent(Long parentId, String parentNickname) {
        log.info("[ getValidatedParent() ]");
        if (parentId == null) return null;

        Comment parent = commentManager.findById(parentId);
        validateParentNickname(parent.getUser(), parentNickname);

        // 부모 댓글이 최상위 댓글인지 확인하여 반환
        return parent.getParent() != null ? parent.getParent() : parent;
    }

    private void validateParentNickname(User user, String parentNickname) {
        log.info("[ validateParentNickname() ]");
        if (!user.getNickname().equals(parentNickname)) {
            throw new CommenterMismatchException(parentNickname);
        }
    }
}
