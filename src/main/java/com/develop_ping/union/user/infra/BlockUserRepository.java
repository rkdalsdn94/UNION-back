package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.entity.BlockUser;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockUserRepository extends JpaRepository<BlockUser, Long> {

    // 1. 차단하기 (BlockUser 엔티티를 save 메서드로 저장하면 됨)

    // 2. 차단 해제하기 (특정 유저와의 차단 관계 삭제)
    void deleteByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);

    // 3. 내가 차단한 유저 목록 조회
    List<BlockUser> findByBlockingUser(User blockingUser);

    // 4. 나를 차단한 유저 목록 조회
    List<BlockUser> findByBlockedUser(User blockedUser);

    // 5. 내가 특정 유저를 차단했는지 확인
    boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser);
}
