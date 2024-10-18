package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.Gathering;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GatheringManagerImpl implements GatheringManager {

    private final GatheringRepository gatheringRepository;

    @Override
    public void getGatheringById(Long id) {
        gatheringRepository.findById(id);
    }

    @Override
    public Gathering saveGathering(Gathering gathering) {
        return null;
    }


    private void extracted(GatheringCommand gathering) {
    }
}
