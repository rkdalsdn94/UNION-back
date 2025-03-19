package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.user.domain.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GatheringManager {

    GatheringResponse save(Gathering gathering);
    Slice<GatheringListResponse> getGatheringList(GatheringListCommand command, User user);
    Gathering findById(Long gatheringId);
    void deleteGathering(Gathering gathering);
    Gathering findWithPessimisticLockById(Long gatheringId);

    Slice<Gathering> getMyGatheringList(User user, Pageable pageable);

    Slice<Gathering> getUserGatheringList(Long userId, Pageable pageable);

    void kickOutUser(Long userId, Long gatheringId, User user);

    List<Gathering> getParticipatedGatheringList(User user);
}
