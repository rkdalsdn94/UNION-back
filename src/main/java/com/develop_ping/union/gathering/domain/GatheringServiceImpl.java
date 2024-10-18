package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GatheringServiceImpl {

    private final GatheringManager gatheringManager;

    public void a(Long id) {
        gatheringManager.getGatheringById(id);
    }

    public Gathering save(GatheringCommand gathering) {
        return gatheringManager.saveGathering(gathering.toEntity());
    }
}
