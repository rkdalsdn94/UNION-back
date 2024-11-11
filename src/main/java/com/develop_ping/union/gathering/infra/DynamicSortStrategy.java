package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.QGathering;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.domain.entity.QParty;
import com.develop_ping.union.user.domain.entity.QBlockUser;
import com.develop_ping.union.user.domain.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

// TODO: 클래스 뭉치가 너무 커졌을 때는 어떻게? (ex: 패턴을 적용한다?)
@Component
public class DynamicSortStrategy implements GatheringSortStrategy {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Gathering> applySort(
        GatheringRepository repository, User user, GatheringListCommand command, Pageable pageable
    ) {
        QGathering gathering = QGathering.gathering;
        QParty party = QParty.party;
        QBlockUser blockUser = QBlockUser.blockUser;
        BooleanBuilder baseBuilder = new BooleanBuilder();
        BooleanBuilder includeUserGatherings = new BooleanBuilder();

        if (command.getKeyword() != null) {
            baseBuilder.and(gathering.title.containsIgnoreCase(command.getKeyword()))
                       .or(gathering.content.containsIgnoreCase(command.getKeyword()));
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        // 차단된 유저와 관련된 gathering ID 조회
        List<Long> blockedGatheringIds = queryFactory.select(party.gathering.id)
                                                     .from(party)
                                                     .join(blockUser)
                                                     .on(party.user.id.eq(blockUser.blockedUser.id)
                                                                      .or(party.user.id.eq(blockUser.blockingUser.id)))
                                                     .where(blockUser.blockingUser.id.eq(user.getId())
                                                                                     .or(blockUser.blockedUser.id.eq(user.getId())))
                                                     .fetch();

        // 본인이 참여한 gathering ID 조회
        List<Long> userGatheringIds = queryFactory.select(party.gathering.id)
                                                  .from(party)
                                                  .where(party.user.id.eq(user.getId())
                                                                      .and(party.role.eq(PartyRole.OWNER)))
                                                  .fetch();

        if (!blockedGatheringIds.isEmpty()) {
            baseBuilder.and(gathering.id.notIn(blockedGatheringIds));
        }

        if (!userGatheringIds.isEmpty()) {
            includeUserGatherings.or(gathering.id.in(userGatheringIds));
        }

        baseBuilder.or(includeUserGatherings);

        // 최종 쿼리에서 페치 조인을 사용하여 N+1 문제를 방지
        JPAQuery<Gathering> query = queryFactory.selectFrom(gathering)
                                                .leftJoin(gathering.parties, party).fetchJoin()
                                                .leftJoin(party.user).fetchJoin()
                                                .where(baseBuilder)
                                                .offset(pageable.getOffset())
                                                .limit(pageable.getPageSize());

        // 하버사인 공식, 거리 계산 및 정렬 조건 적용
        NumberExpression<Double> distanceExpression = Expressions.numberTemplate(Double.class,
            "6371 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1})))",
            command.getLatitude(), gathering.place.latitude, gathering.place.longitude, command.getLongitude()
        );

        if (command.getSortType() == SortType.LATEST) {
            query.orderBy(gathering.createdAt.desc());
        } else if (command.getSortType() == SortType.DISTANCE) {
            query.orderBy(distanceExpression.asc());
        } else if (command.getSortType() == SortType.GATHERING_DATE) {
            query.orderBy(gathering.gatheringDateTime.asc());
        }

        List<Gathering> results = query.fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results.remove(results.size() - 1); // 다음 페이지 확인을 위해 추가로 가져온 항목 제거
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
}
