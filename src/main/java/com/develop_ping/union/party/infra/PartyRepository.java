package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByGatheringAndUser(Gathering gathering, User user);
    Optional<Party> findByGathering(Gathering gathering);
    void deleteByGathering(Gathering gathering);
    boolean existsByGatheringAndUser(Gathering gathering, User user);

    // 주최자 닉네임을 Gathering과 Role 기준으로 조회
    @Query("SELECT u.nickname FROM Party p JOIN p.user u WHERE p.gathering = :gathering AND p.role = :role")
    String findOwnerNicknameByGatheringAndRole(@Param("gathering") Gathering gathering, @Param("role") PartyRole role);

    // 주최자 여부 확인
    boolean existsByGatheringAndUserAndRole(Gathering gathering, User user, PartyRole role);

    // 모임 참여자 삭제 - 나가기 기능
    void deleteByGatheringAndUser(Gathering gathering, User user);
}
