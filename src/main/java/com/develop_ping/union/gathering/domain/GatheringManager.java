package com.develop_ping.union.gathering.domain;

// 추상화 요놈이 infra layer 이름만 store다.
public interface GatheringManager {

    void getGatheringById(Long id);
    Gathering saveGathering(Gathering gathering);
}
