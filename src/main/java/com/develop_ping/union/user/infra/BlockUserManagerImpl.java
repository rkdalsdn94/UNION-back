package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.BlockUserManager;
import com.develop_ping.union.user.domain.entity.BlockUser;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.BlockRelationshipNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    public void blockUser(User blockingUser, User blockedUser) {
        BlockUser user = BlockUser.of(blockingUser, blockedUser);
        blockUserRepository.save(user);
    }

    @Override
    public void unblockUser(User blockingUser, User blockedUser) {
        if (existsByBlockingUserAndBlockedUser(blockingUser, blockedUser)) {
            blockUserRepository.deleteByBlockingUserAndBlockedUser(blockingUser, blockedUser);
        } else throw new BlockRelationshipNotFoundException(blockingUser.getNickname(), blockedUser.getNickname());
    }

    @Override
    public List<User> findAllBlockedUser(User user) {
        // blockingUser가 차단한 BlockUser 목록 조회
        List<BlockUser> blockUserList = blockUserRepository.findByBlockingUser(user);

        // blockedUser 필드만 추출하여 User 리스트로 반환
        return blockUserList.stream()
                .map(BlockUser::getBlockedUser)
                .toList();
    }

    @Override
    public List<User> findAllBlockedOrBlockingUser(User user) {
        // user가 차단한 유저 목록
        List<BlockUser> blockedUsers = blockUserRepository.findByBlockingUser(user);

        // user를 차단한 유저 목록
        List<BlockUser> blockingUsers = blockUserRepository.findByBlockedUser(user);

        // 차단한 유저와 차단당한 유저들을 중복 없이 Set으로 모음
        Set<User> uniqueUsers = new HashSet<>();

        // blockedUser 필드를 추출하여 추가
        for (BlockUser blockUser : blockedUsers) {
            uniqueUsers.add(blockUser.getBlockedUser());
        }

        // blockingUser 필드를 추출하여 추가
        for (BlockUser blockUser : blockingUsers) {
            uniqueUsers.add(blockUser.getBlockingUser());
        }

        // Set을 List로 변환하여 반환
        return new ArrayList<>(uniqueUsers);
    }

    @Override
    public boolean existsByBlockingUserAndBlockedUser(User blockingUser, User blockedUser) {
        return blockUserRepository.existsByBlockingUserAndBlockedUser(blockingUser, blockedUser);
    }
}
