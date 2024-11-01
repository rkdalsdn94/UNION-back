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

import java.util.Optional;

@Repository
public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    /**
     * 조회수 증가
     * TODO: 성능 이슈가 발생할 수 있을거 같음.. 추후 redis로 빼야 되나?
     * @param gatheringId 모임 게시글 ID
     */
    @Modifying
    @Query("UPDATE Gathering g SET g.views = g.views + 1 WHERE g.id = :gatheringId")
    void incrementViewCount(@Param("gatheringId") Long gatheringId);

    /**
     * 모임 게시글 생성 시간을 기준으로 정렬
     * @param pageable 페이징 정보
     * @return Slice<Gathering>
     */
    Slice<Gathering> findByOrderByCreatedAtDesc(Pageable pageable);

    /**
     * 하버사인(Haversine) 공식을 이용, 관련된 글은 PR 또는 이슈 참고
     * 위도, 경도를 기준으로 모임 게시글 정렬
     * @param latitude 위도
     * @param longitude 경도
     * @param pageable 페이징 정보
     * @return Slice<Gathering>
     */
    @Query(
        value = """
                SELECT g.*,
                    (6371 * acos(cos(radians(:latitude))
                          * cos(radians(g.latitude))
                          * cos(radians(g.longitude) - radians(:longitude))
                          + sin(radians(:latitude))
                          * sin(radians(g.latitude)))) AS distance
                FROM gatherings g
                ORDER BY
                    CASE
                        WHEN g.latitude IS NULL OR g.longitude IS NULL THEN 1
                        ELSE 0
                    END,
                    distance
            """,
        countQuery = """
                SELECT COUNT(*) FROM gatherings
            """,
        nativeQuery = true)
    Slice<Gathering> findByDistance(@Param("latitude") Double latitude,
                                    @Param("longitude") Double longitude,
                                    Pageable pageable);

    /**
     * 현재 시간 이후의 모임 게시글을 조회
     * @param pageable 페이징 정보
     * @return Slice<Gathering>
     */
    @Query("SELECT g FROM Gathering g WHERE g.gatheringDateTime > CURRENT_TIMESTAMP ORDER BY g.gatheringDateTime ASC")
    Slice<Gathering> findByGatheringDateTimeAsc(Pageable pageable);

    // TODO: 비관적 락 -> 낙관적 락으로 개선 필요 (동시성 이슈가 많이 발생하지 않을 것 같음)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT g FROM Gathering g WHERE g.id = :gatheringId")
    Optional<Gathering> findWithPessimisticLockById(@Param("gatheringId") Long gatheringId);

    @Query("""
    SELECT g FROM Gathering g JOIN g.parties p
    WHERE p.user = :user AND p.role = 'owner' ORDER BY g.id DESC
    """)
    Slice<Gathering> findByUserAsOwner(@Param("user") User user, Pageable pageable);
}
