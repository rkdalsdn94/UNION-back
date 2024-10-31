package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.infra.strategy.LatestSortStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GatheringSortStrategyFactory {

    private final Map<SortType, GatheringSortStrategy> strategies;

    public GatheringSortStrategyFactory(List<GatheringSortStrategy> strategyList) {
        this.strategies = strategyList.stream()
                                      .collect(
                                          Collectors.toMap(
                                              GatheringSortStrategy::getSortType, strategy -> strategy
                                          )
                                      );
    }

    public GatheringSortStrategy getStrategy(SortType sortType) {
        return strategies.getOrDefault(sortType, new LatestSortStrategy()); // 기본값 설정
    }
}
