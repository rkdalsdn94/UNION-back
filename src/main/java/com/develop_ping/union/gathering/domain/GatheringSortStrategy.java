package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.infra.GatheringRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * TODO: 현재는 각 조건별로 다르게 실행된다. 고도화 때 동적 쿼리로 변경할 수 있다면 변경해보자.
 *    ex) 현재는 모임 날짜 기준으로 정렬 조건을 세우면 서울에서 검색했어도 부산에 대한 정보가 나온다.
 */
public interface GatheringSortStrategy {

    SortType getSortType();

    Slice<Gathering> applySort(GatheringRepository repository, GatheringListCommand command, Pageable pageable);
}
