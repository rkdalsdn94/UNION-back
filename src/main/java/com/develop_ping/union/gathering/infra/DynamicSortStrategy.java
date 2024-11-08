package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.QGathering;
import com.develop_ping.union.gathering.exception.NoMatchingResultsException;
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
        GatheringRepository repository, GatheringListCommand command, Pageable pageable
    ) {
        QGathering gathering = QGathering.gathering;
        BooleanBuilder builder = new BooleanBuilder();

        // 검색어가 있는 경우에만 조건 추가
        if (command.getKeyword() != null) {
            builder.and(gathering.title.containsIgnoreCase(command.getKeyword()))
                   .or(gathering.content.containsIgnoreCase(command.getKeyword()));
        }

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        // keyword 조건으로 검색 결과 확인
        List<Gathering> keywordResults = queryFactory.selectFrom(gathering)
                                                     .where(builder)
                                                     .fetch();

        // 검색어에 일치하는 데이터가 없는 경우 예외 발생
        if (command.getKeyword() != null && keywordResults.isEmpty()) {
            throw new NoMatchingResultsException("일치하는 항목이 없습니다.");
        }

        // 기본 필터, 정렬 조건 적용하여 쿼리 재작성
        JPAQuery<Gathering> query = queryFactory.selectFrom(gathering)
                                                .where(builder)
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
