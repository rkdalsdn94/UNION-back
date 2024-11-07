package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.CommentManager;
import com.develop_ping.union.comment.domain.dto.*;
import com.develop_ping.union.comment.domain.entity.Comment;
import com.develop_ping.union.comment.exception.CommentPermissionDeniedException;
import com.develop_ping.union.comment.exception.CommenterMismatchException;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.BlockUserManager;
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
    private final BlockUserManager blockUserManager;
    private final ReactionManager reactionManager;

    @Override
    @Transactional
    public CommentInfo createComment(CommentCommand command) {
        log.info("[ CommentService.createComment() ] user id: {}", command.getUser().getId());

        User user = command.getUser();
        Post post = postManager.findById(command.getPostId());
        Comment parent = getValidatedParent(command.getParentId(), command.getParentNickname());

        Comment comment = commentManager.save(Comment.of(command.getContent(), post, user, parent, command.getParentNickname()));

        log.info("[ New Comment! ] comment id: {}", comment.getId());
        return CommentInfo.from(comment);
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

        log.info("[ Comment Update Completed! ] comment id: {}", updatedComment.getId());
        return CommentInfo.from(updatedComment);
    }

    @Override
    @Transactional
    public void deleteComment(CommentCommand command) {
        log.info("[ CommentService.deleteComment() ] comment id: {}", command.getId());

        Comment comment = commentManager.findById(command.getId());
        User user = command.getUser();

        validateCommentOwner(user, comment);

        // TODO: 삭제하려는 댓글이 부모 댓글일 경우 정보 바꾸기!!!!!!!! soft delete 적용 ㅎㅎ~
        // TODO: 근데 자식 댓글인 경우에는 그냥 삭제하기 -> hard delete??? 되나..?

        commentManager.delete(comment);
        log.info("[ Comment Delete Completed! ]");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentInfo> getCommentsByPostId(CommentCommand command) {
        log.info("[ CommentService.getCommentsByPostId() ] post id: {}", command.getPostId());

        Post post = postManager.findById(command.getPostId());
        List<Comment> comments = commentManager.findByPostId(post.getId());

        List<User> blockUsers = blockUserManager.findAllBlockedOrBlockingUser(command.getUser());

        // TODO: rootComments 랑 children 을 각각 for문이나 stream 돌려서 User deleted 확인 + blockUsers 확인해서 제외(null처리)해주기

        long count = commentManager.countByPostId(post.getId());

        log.info("[ Comments Retrieval Completed! ]");
        return comments.stream().map(comment -> {
            long commentLikes = reactionManager.countLikesByComment(comment.getId());
            boolean isLiked = reactionManager.existsByUserIdAndTypeAndId(
                    command.getUser().getId(),
                    ReactionType.COMMENT,
                    comment.getId());

            return CommentInfo.of(comment, commentLikes, isLiked);
        }).toList();
    }

    @Override
    @Transactional
    public boolean likeComment(CommentCommand command) {
        log.info("[ CommentService.likeComment() ] comment id: {}, user nickname: {}",
                command.getId(), command.getUser().getNickname());

        return reactionManager.likeComment(command.getUser(), command.getId()) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentInfo getBestComment(CommentCommand command) {
        log.info("[ CommentService.getBestComment() ] post id: {}", command.getPostId());
        Comment bestComment = commentManager.findBestComment(command.getPostId());

        if (bestComment == null) { return null; }

        long commentLikes = reactionManager.countLikesByComment(bestComment.getId());
        boolean isLiked = reactionManager.existsByUserIdAndTypeAndId(
                bestComment.getUser().getId(),
                ReactionType.COMMENT,
                bestComment.getId());

        return CommentInfo.of(bestComment, commentLikes, isLiked);
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
