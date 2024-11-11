package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.infra.GatheringRepository;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GatheringSortStrategy {

    Slice<Gathering> applySort(GatheringRepository repository, User user, GatheringListCommand command, Pageable pageable);
}
