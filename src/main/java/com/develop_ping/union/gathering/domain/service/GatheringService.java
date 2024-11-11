package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringHotListInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GatheringService {
    GatheringInfo createGathering(GatheringCommand command, User user);
    GatheringDetailInfo getGatheringDetail(Long gatheringId, User user);
    void updateGathering(Long gatheringId, GatheringCommand command, User user);
    Slice<GatheringListInfo> getGatheringList(GatheringListCommand request, User user);
    void deleteGathering(Long gatheringId, User user);
    void joinGathering(Long gatheringId, User user);

    void exitGathering(Long gatheringId, User user);

    Slice<GatheringListInfo> getMyGatheringList(User user, Pageable pageable);

    Slice<GatheringListInfo> getUserGatheringList(String userToken, Pageable pageable);

    GatheringInfo recruitedGathering(Long gatheringId, User user);

    void kickOutUser(String userToken, Long gatheringId, User user);

    List<GatheringListInfo> getParticipatedGatheringList(User user);

    Long likeGathering(Long gatheringId, User user);

    Slice<GatheringHotListInfo> getHotGatheringList(Pageable pageable);
}
