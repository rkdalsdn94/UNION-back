package com.develop_ping.union.reaction.infra;

import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionManagerImpl implements ReactionManager {

    private final ReactionRepository reactionRepository;

    @Override
    public Long selectLikeCount(Long gatheringId) {
        return reactionRepository.countByTypeAndGatheringId(ReactionType.GATHERING, gatheringId);
    }

    @Override
    public boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId) {
        return reactionRepository.existsByUserIdAndTypeAndId(userId, type, gatheringId);
    }
}
