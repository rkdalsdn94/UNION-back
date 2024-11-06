package com.develop_ping.union.comment.infra;

import com.develop_ping.union.comment.domain.CommentManager;
import com.develop_ping.union.comment.domain.entity.Comment;
import com.develop_ping.union.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentManagerImpl implements CommentManager {
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotFoundException(id));
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findByPostIdAndParentIsNull(Long postId) {
        return commentRepository.findByPostIdAndParentIsNull(postId);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public long countByPostId(Long postId) {
        return commentRepository.countByPostId(postId);
    }

    @Override
    public Comment findBestComment(Long postId) {
        Optional<Comment> comment = commentRepository.findTopByPostIdAndReactionType(postId, "COMMENT");
        return comment.orElse(null);
    }
}
