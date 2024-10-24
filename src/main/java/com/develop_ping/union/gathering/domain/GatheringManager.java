package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;

import java.util.Optional;

// 추상화 요놈이 infra layer 이름만 store다.
public interface GatheringManager {

    GatheringInfo createGathering(Gathering command);

    GatheringInfo getGatheringDetail(Long gatheringId);
}
