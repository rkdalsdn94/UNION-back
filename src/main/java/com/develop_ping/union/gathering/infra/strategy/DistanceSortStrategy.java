package com.develop_ping.union.gathering.infra.strategy;

import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.infra.GatheringRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class DistanceSortStrategy implements GatheringSortStrategy {

    @Override
    public SortType getSortType() {
        return SortType.DISTANCE;
    }

    @Override
    public Slice<Gathering> applySort(
        GatheringRepository repository, GatheringListCommand command, Pageable pageable
    ) {
        return repository.findByDistance(command.getLatitude(), command.getLongitude(), pageable);
    }
}
