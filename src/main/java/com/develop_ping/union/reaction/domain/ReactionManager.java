package com.develop_ping.union.reaction.domain;

import com.develop_ping.union.reaction.domain.entity.ReactionType;

import com.develop_ping.union.user.domain.entity.User;

public interface ReactionManager {
    Long selectLikeCount(Long gatheringId);

    boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId);

    Long likePost(User user, Long postId);
    Long likeGathering(User user, Long gatheringId);

    long countLikesByPost(Long targetId);
    long countLikesByGathering(Long targetId);
}
