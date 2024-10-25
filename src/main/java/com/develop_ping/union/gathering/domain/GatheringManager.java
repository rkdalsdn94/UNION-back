package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;

import java.util.Optional;

public interface GatheringManager {

    GatheringInfo createGathering(Gathering command);

    GatheringInfo getGatheringDetail(Long gatheringId);
}
