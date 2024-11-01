package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByGatheringIdAndUserId(Long id, Long userId);
    Optional<Party> findByGatheringId(Long gatheringId);
    void deleteByGatheringId(Long gatheringId);
    boolean existsByGatheringIdAndUserId(Long gatheringId, Long userId);

    // 주최자 닉네임을 GatheringId와 Role 기준으로 조회
    @Query("SELECT u.nickname FROM Party p JOIN User u ON p.userId = u.id WHERE p.gatheringId = :gatheringId AND p.role = :role")
    String findOwnerNicknameByGatheringIdAndRole(@Param("gatheringId") Long gatheringId, @Param("role") PartyRole role);

    // 주최자 여부 확인
    boolean existsByGatheringIdAndUserIdAndRole(Long gatheringId, Long userId, PartyRole role);

    // 모임 참여자 삭제 - 나가기 기능
    void deleteByGatheringIdAndUserId(Long gatheringId, Long userId);
}
