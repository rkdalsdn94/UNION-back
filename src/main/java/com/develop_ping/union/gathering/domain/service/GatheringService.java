package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.GatheringInfo;

public interface GatheringService {

    GatheringInfo createGathering(GatheringCommand command);
}
