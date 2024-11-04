package com.develop_ping.union.reaction.infra;

import com.develop_ping.union.reaction.domain.entity.Reaction;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    boolean existsByUserIdAndTypeAndId(Long userId, ReactionType type, Long gatheringId);

    long countByTypeAndTargetId(ReactionType type, Long targetId);

    List<Reaction> findByTypeAndTargetId(ReactionType type, Long targetId);

    Optional<Reaction> findByUserAndTypeAndTargetId(User user, ReactionType type, Long targetId);
}
