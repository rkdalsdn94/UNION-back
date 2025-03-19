package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.QGathering;
import com.develop_ping.union.gathering.infra.response.GatheringWithLikes;
import com.develop_ping.union.reaction.domain.entity.QReaction;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotGatheringQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Slice<GatheringWithLikes> findHotGatheringList(long POPULAR_GATHERING_REACTION_COUNT, Pageable pageable) {
        QGathering gathering = QGathering.gathering;
        QReaction reaction = QReaction.reaction;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        // 좋아요 2개 이상 인기 모임 + 각 모임의 좋아요 수 조회, 좋아요 수가 높은 순으로 정렬 + 모임 시간 순으로 정렬
        List<GatheringWithLikes> query = queryFactory.select(Projections.constructor(GatheringWithLikes.class, gathering, reaction.count()))
                                                     .from(gathering)
                                                     .join(reaction)
                                                     .on(reaction.type.eq(ReactionType.GATHERING)
                                                                      .and(reaction.targetId.eq(gathering.id)))
                                                     .groupBy(gathering.id)
                                                     .having(reaction.count().goe(POPULAR_GATHERING_REACTION_COUNT))
                                                     .orderBy(reaction.count()
                                                                      .desc(), gathering.gatheringDateTime.asc())
                                                     .offset(pageable.getOffset())
                                                     .limit(pageable.getPageSize())
                                                     .fetch();
        return new SliceImpl<>(query, pageable, true);
    }
}
