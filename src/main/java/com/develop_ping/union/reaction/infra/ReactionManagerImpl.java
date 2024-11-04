package com.develop_ping.union.reaction.infra;

import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.reaction.domain.entity.Reaction;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReactionManagerImpl implements ReactionManager {

    private final ReactionRepository reactionRepository;

    @Override
    public Long selectLikeCount(Long gatheringId) {
        return reactionRepository.countByTypeAndGatheringId(ReactionType.GATHERING, gatheringId);
    }

    // 좋아요 기능
    @Override
    public boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId) {
        return reactionRepository.existsByUserIdAndTypeAndId(userId, type, gatheringId);
    }

    @Override
    public Long likePost(User user, Long postId) {
        ReactionType type = ReactionType.POST;
        return toggleReaction(user, postId, type);
    }

    @Override
    public Long likeGathering(User user, Long gatheringId) {
        ReactionType type = ReactionType.GATHERING;
        return toggleReaction(user, gatheringId, type);
    }

    @Override
    public Long likeComment(User user, Long commentId) {
        ReactionType type = ReactionType.COMMENT;
        return toggleReaction(user, commentId, type);
    }

    // 좋아요 개수 조회
    @Override
    public long countLikesByPost(Long postId) {
        return reactionRepository.countByTypeAndTargetId(ReactionType.POST, postId);
    }

    @Override
    public long countLikesByGathering(Long gatheringId) {
        return reactionRepository.countByTypeAndTargetId(ReactionType.GATHERING, gatheringId);
    }

    @Override
    public long countLikesByComment(Long commentId) {
        return reactionRepository.countByTypeAndTargetId(ReactionType.COMMENT, commentId);
    }

    // 좋아요 목록 조회
    @Override
    public List<Reaction> findLikesByPost(Long postId) {
        return reactionRepository.findByTypeAndTargetId(ReactionType.POST, postId);
    }

    @Override
    public List<Reaction> findLikesByGathering(Long gatheringId) {
        return reactionRepository.findByTypeAndTargetId(ReactionType.GATHERING, gatheringId);
    }

    @Override
    public List<Reaction> findLikesByComment(Long commentId) {
        return reactionRepository.findByTypeAndTargetId(ReactionType.COMMENT, commentId);
    }

    private Long toggleReaction(User user, Long targetId, ReactionType type) {
        Optional<Reaction> existingReaction = reactionRepository.findByUserAndTypeAndTargetId(user, type, targetId);

        // 이미 누른 Reaction이 있다면 삭제, 없으면 저장
        if (existingReaction.isPresent()) {
            reactionRepository.delete(existingReaction.get());
            log.info("[ {} Like Removed ] targetId: {}, userId: {}", type, targetId, user.getId());
            return null;
        } else {
            Reaction reaction = reactionRepository.save(Reaction.of(user, targetId, type));
            log.info("[ {} Liked Successfully ] targetId: {}, userId: {}", type, targetId, user.getId());
            return reaction.getId();
        }
    }
}
