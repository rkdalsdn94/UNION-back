package com.develop_ping.union.reaction.domain;

import com.develop_ping.union.reaction.domain.entity.ReactionType;

import com.develop_ping.union.reaction.domain.entity.Reaction;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface ReactionManager {

    boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId);
    boolean existsPostLikeByUserId(Long userId, Long postId);
    boolean existsCommentLikeByUserId(Long userId, Long commentId);

    Long likePost(User user, Long postId);
    Long likeGathering(User user, Long gatheringId);
    Long likeComment(User user, Long commentId);

    long countLikesByPost(Long postId);
    long countLikesByGathering(Long gatheringId);
    long countLikesByComment(Long commentId);

    List<Reaction> findLikesByPost(Long postId);
    List<Reaction> findLikesByGathering(Long gatheringId);
    List<Reaction> findLikesByComment(Long commentId);
}
