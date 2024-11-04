package com.develop_ping.union.reaction.infra;

import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.reaction.domain.entity.Reaction;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public Long likePost(User user, Long postId) {
        ReactionType type = ReactionType.POST;
        return saveReaction(user, postId, type);
    }

    @Override
    public Long likeGathering(User user, Long gatheringId) {
        ReactionType type = ReactionType.GATHERING;
        return saveReaction(user, gatheringId, type);
    }

    @Override
    public long countLikesByPost(Long targetId) {
        return reactionRepository.countByTypeAndTargetId(ReactionType.POST, targetId);
    }

    @Override
    public long countLikesByGathering(Long targetId) {
        return reactionRepository.countByTypeAndTargetId(ReactionType.GATHERING, targetId);
    }

    private Long saveReaction(User user, Long targetId, ReactionType type) {
        Reaction reaction = reactionRepository.save(Reaction.of(user, targetId, type));
        log.info("[ {} Liked Successfully ] targetId: {}, userId: {}", type, targetId, user.getId());
        return reaction.getId();
    }
}
