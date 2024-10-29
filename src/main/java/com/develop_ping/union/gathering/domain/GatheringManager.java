package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.strategy.GatheringSortStrategy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GatheringManager {

    GatheringInfo createGathering(Gathering command);

    GatheringInfo getGatheringDetail(Long gatheringId);

    Slice<Gathering> getGatheringList(GatheringSortStrategy strategy, GatheringListCommand command, Pageable pageable);
}
