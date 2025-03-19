package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.user.domain.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GatheringService {

    GatheringResponse createGathering(GatheringCommand command, User user);

    GatheringDetailResponse getGatheringDetail(Long gatheringId, User user);

    void updateGathering(Long gatheringId, GatheringCommand command, User user);

    Slice<GatheringListResponse> getGatheringList(GatheringListCommand request, User user);

    void deleteGathering(Long gatheringId, User user);

    void joinGathering(Long gatheringId, User user);

    void exitGathering(Long gatheringId, User user);

    Slice<GatheringListResponse> getMyGatheringList(User user, Pageable pageable);

    Slice<GatheringListResponse> getUserGatheringList(Long userId, Pageable pageable);

    GatheringResponse recruitedGathering(Long gatheringId, User user);

    void kickOutUser(Long userId, Long gatheringId, User user);

    List<GatheringListResponse> getParticipatedGatheringList(User user);
}
