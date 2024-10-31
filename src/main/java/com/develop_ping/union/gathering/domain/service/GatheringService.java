package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import org.springframework.data.domain.Slice;

public interface GatheringService {

    GatheringInfo createGathering(GatheringCommand command, Long userId);
    GatheringDetailInfo getGatheringDetail(Long gatheringId, Long userId);
    GatheringInfo updateGathering(Long gatheringId, GatheringCommand command, Long userId);
    Slice<GatheringListInfo> getGatheringList(GatheringListCommand request);
}
