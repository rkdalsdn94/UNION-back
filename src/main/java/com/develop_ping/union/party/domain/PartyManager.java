package com.develop_ping.union.party.domain;

import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;

public interface PartyManager {

    PartyInfo createParty(Long gatheringId, Long userId);
    Party findByGatheringId(Long gatheringId);
    void deleteParty(Long gatheringId);
    void joinGathering(Long gatheringId, Long userId);

    // 주최자 닉네임 가져오기
    String findOwnerNicknameByGatheringId(Long gatheringId);

    // 주최자인지 여부 확인
    boolean existsByGatheringIdAndUserIdAndRole(Long gatheringId, Long userId, PartyRole role);

    // 모임 참여 여부 확인
    boolean existsByGatheringIdAndUserId(Long gatheringId, Long userId);

    // 모임 참여자 삭제 - 나가기 기능
    void deleteByGatheringIdAndUserId(Long gatheringId, Long userId);
}
