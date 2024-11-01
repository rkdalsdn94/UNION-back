package com.develop_ping.union.party.domain;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;

public interface PartyManager {

    PartyInfo createParty(Gathering gathering, User user);
    Party findByGatheringAndUser(Gathering gathering, User user);
    void deleteParty(Gathering gathering);
    void joinGathering(Gathering gathering, User user);

    // 주최자 닉네임 가져오기
    String findOwnerNicknameByGathering(Gathering gathering);

    // 주최자인지 여부 확인
    boolean existsByGatheringAndUserAndRole(Gathering gathering, User user, PartyRole role);

    // 모임 참여 여부 확인
    boolean existsByGatheringAndUser(Gathering gathering, User user);

    // 모임 참여자 삭제 - 나가기 기능
    void deleteByGatheringAndUser(Gathering gathering, User user);

    void delete(Party party);
}
