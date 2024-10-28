package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;

public interface GatheringManager {

    GatheringInfo createGathering(Gathering command);

    GatheringInfo getGatheringDetail(Long gatheringId);
}
