package com.develop_ping.union.reaction.infra;

import com.develop_ping.union.reaction.domain.entity.Reaction;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.type = :type AND r.targetId = :gatheringId")
    long countByTypeAndGatheringId(@Param("type") ReactionType type, @Param("gatheringId") Long gatheringId);

    boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId);

    long countByTypeAndTargetId(ReactionType type, Long targetId);

    List<Reaction> findByTypeAndTargetId(ReactionType type, Long targetId);

    Optional<Reaction> findByUserAndTypeAndTargetId(User user, ReactionType type, Long targetId);
}
