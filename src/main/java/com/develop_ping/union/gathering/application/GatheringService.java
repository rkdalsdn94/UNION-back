package com.develop_ping.union.gathering.application;

import com.develop_ping.union.gathering.application.dto.GatheringCommand;
import com.develop_ping.union.gathering.application.dto.GatheringInfo;

public interface GatheringService {

    GatheringInfo createGathering(GatheringCommand command);
}
