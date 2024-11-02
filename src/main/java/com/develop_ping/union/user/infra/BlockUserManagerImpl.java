package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.BlockUserManager;
import com.develop_ping.union.user.domain.entity.BlockUser;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.BlockRelationshipNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class BlockUserManagerImpl implements BlockUserManager {
    private final BlockUserRepository blockUserRepository;

    @Override
    @Transactional
    public void blockUser(User blockingUser, User blockedUser) {
        log.info("유저 [{}]가 유저 [{}]를 차단합니다.", blockingUser.getNickname(), blockedUser.getNickname());
        BlockUser user = BlockUser.of(blockingUser, blockedUser);
        blockUserRepository.save(user);
        log.info("유저 [{}]가 유저 [{}]를 성공적으로 차단했습니다.", blockingUser.getNickname(), blockedUser.getNickname());
    }

    @Override
    @Transactional
    public void unblockUser(User blockingUser, User blockedUser) {
        if (existsByBlockingUserAndBlockedUser(blockingUser, blockedUser)) {
            log.info("유저 [{}]가 유저 [{}]의 차단을 해제합니다.", blockingUser.getNickname(), blockedUser.getNickname());
            blockUserRepository.deleteByBlockingUserAndBlockedUser(blockingUser, blockedUser);
            log.info("유저 [{}]의 차단이 성공적으로 해제되었습니다.", blockedUser.getNickname());
        } else {
            log.info("유저 [{}]가 유저 [{}]를 차단하지 않았으므로 차단 해제가 불가능합니다.", blockingUser.getNickname(), blockedUser.getNickname());
            throw new BlockRelationshipNotFoundException(blockingUser.getNickname(), blockedUser.getNickname());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllBlockedUser(User user) {
        log.info("유저 [{}]가 차단한 유저 목록을 조회합니다.", user.getNickname());
        List<BlockUser> blockUserList = blockUserRepository.findByBlockingUser(user);
        List<User> blockedUsers = blockUserList.stream()
                .map(BlockUser::getBlockedUser)
                .toList();
        log.info("유저 [{}]가 차단한 유저는 총 [{}]명입니다.", user.getNickname(), blockedUsers.size());
        return blockedUsers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllBlockedOrBlockingUser(User user) {
        log.info("유저 [{}]와 차단 관계에 있는 모든 유저를 조회합니다.", user.getNickname());
        List<BlockUser> blockedUsers = blockUserRepository.findByBlockingUser(user);
        List<BlockUser> blockingUsers = blockUserRepository.findByBlockedUser(user);

        Set<User> uniqueUsers = new HashSet<>();
        for (BlockUser blockUser : blockedUsers) {
            uniqueUsers.add(blockUser.getBlockedUser());
        }
        for (BlockUser blockUser : blockingUsers) {
            uniqueUsers.add(blockUser.getBlockingUser());
        }

        log.info("유저 [{}]와 차단 관계에 있는 유저는 총 [{}]명입니다.", user.getNickname(), uniqueUsers.size());
        return new ArrayList<>(uniqueUsers);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser) {
        log.info("유저 [{}]가 유저 [{}]를 차단했는지 여부를 확인합니다.", blockingUser.getNickname(), blockedUser.getNickname());
        boolean exists = blockUserRepository.existsByBlockingUserAndBlockedUser(blockingUser, blockedUser);
        if (exists) {
            log.info("유저 [{}]는 유저 [{}]를 차단한 상태입니다.", blockingUser.getNickname(), blockedUser.getNickname());
        } else {
            log.info("유저 [{}]는 유저 [{}]를 차단하지 않은 상태입니다.", blockingUser.getNickname(), blockedUser.getNickname());
        }
        return exists;
    }

    @Override
    @Transactional
    public void deletedByUserInvolved(User user) {
        log.info("유저와 관련된 차단 기록 삭제 시도: 유저 ID = {}", user.getId());
        blockUserRepository.deleteByUserInvolved(user);
        log.info("유저와 관련된 차단 기록 삭제 완료: 유저 ID = {}", user.getId());
    }
}

