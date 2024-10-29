package com.develop_ping.union.gathering.domain.strategy;

import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.infra.GatheringRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;


@Component
public class LatestSortStrategy implements GatheringSortStrategy {

    @Override
    public SortType getSortType() {
        return SortType.LATEST;
    }

    @Override
    public Slice<Gathering> applySort(GatheringRepository repository, GatheringListCommand command, Pageable pageable) {
        return repository.findByOrderByCreatedAtDesc(pageable);
    }
}
