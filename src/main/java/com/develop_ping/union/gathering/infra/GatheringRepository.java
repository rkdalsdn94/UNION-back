package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 추상화 (jpa)
@Repository
public interface GatheringRepository extends JpaRepository<Gathering, Long> {
}
