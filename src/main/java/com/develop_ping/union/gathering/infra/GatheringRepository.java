package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    // TODO: 비관적 락 -> 낙관적 락으로 개선 필요 (동시성 이슈가 많이 발생하지 않을 것 같음)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT g FROM Gathering g WHERE g.id = :gatheringId")
    Optional<Gathering> findWithPessimisticLockById(@Param("gatheringId") Long gatheringId);

    // FETCH JOIN을 이용하여 모임 게시글과 파티 정보를 함께 조회(N + 1 문제 해결)
    @Query("""
        SELECT g FROM Gathering g JOIN FETCH g.parties p
        WHERE p.user = :user AND p.role = 'owner' ORDER BY g.id DESC
    """)
    Slice<Gathering> findByUserAsOwner(@Param("user") User user, Pageable pageable);

    @Query("SELECT g FROM Gathering g JOIN FETCH g.parties p WHERE p.user.id = :userId ORDER BY g.id DESC")
    List<Gathering> findGatheringsByUserId(@Param("userId") Long userId);
}
