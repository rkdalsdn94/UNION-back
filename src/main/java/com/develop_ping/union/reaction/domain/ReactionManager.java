package com.develop_ping.union.reaction.domain;

import com.develop_ping.union.reaction.domain.entity.ReactionType;

public interface ReactionManager {

    Long selectLikeCount(Long gatheringId);

    boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId);
}
