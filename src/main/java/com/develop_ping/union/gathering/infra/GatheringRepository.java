package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    // 조회수 증가 쿼리
    @Modifying
    @Query("UPDATE Gathering g SET g.views = g.views + 1 WHERE g.id = :gatheringId")
    void incrementViewCount(@Param("gatheringId") Long gatheringId);
}
