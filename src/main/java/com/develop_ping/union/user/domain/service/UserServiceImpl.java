package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.auth.domain.OAuthUnlinkManager;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.user.domain.BlockUserManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.dto.UserCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.entity.BlockUser;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.DuplicateNicknameException;
import com.develop_ping.union.user.exception.UserBlockedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    public static Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(30);
    public static Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);

    private final OauthUserManager oauthUserManager;
    private final UserManager userManager;
    private final TokenManager tokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final OAuthUnlinkManager oAuthUnlinkManager;
    private final BlockUserManager blockUserManager;

    @Override
    public UserInfo signUp(UserCommand command) {
        // 유저 임시 토큰 검증
        log.info("임시 사용자 토큰으로 사용자 조회 시작");
        OauthUser oauthUser = oauthUserManager.findByToken(command.getOauthUserToken());

        // 닉네임 중복 체크
        if (userManager.existsByNickname(command.getNickname())) {
            throw new DuplicateNicknameException(command.getNickname());
        }

        // 유저 생성
        log.info("새로운 사용자 생성 시작");
        User user = User.of(command, oauthUser.getEmail(), oauthUser.getProfileImage(), oauthUser.getProvider());
        userManager.save(user);
        log.info("새로운 사용자 생성 완료: {}", user.getId());

        // JWT 토큰 생성
        log.info("JWT 액세스 토큰 및 리프레시 토큰 생성 시작");
        String accessToken = tokenManager.generateToken(user, ACCESS_TOKEN_DURATION);
        String refreshToken = tokenManager.generateToken(user, REFRESH_TOKEN_DURATION);
        refreshTokenManager.saveRefreshToken(user, refreshToken);
        log.info("JWT 토큰 생성 완료: 사용자 ID - {}", user.getId());

        // DTO 변환
        return UserInfo.of(user, accessToken, refreshToken);
    }

    @Override
    public UserInfo updateUser(UserCommand command, User user) {
        log.info("사용자 정보 업데이트 요청: 사용자 ID - {}", user.getId());

        // 닉네임 중복 체크
        if (userManager.existsByNickname(command.getNickname())) {
            throw new DuplicateNicknameException(command.getNickname());
        }

        user.update(command.getNickname(), command.getDescription(), command.getProfileImage());
        User updatedUser = userManager.save(user);
        log.info("사용자 정보 업데이트 완료: 사용자 ID - {}", updatedUser.getId());
        return UserInfo.of(updatedUser);
    }

    @Override
    public void signOut(User user) {
        log.info("사용자 로그아웃 요청: 사용자 ID - {}", user.getId());
        refreshTokenManager.deleteByUserId(user);
        log.info("사용자 로그아웃 처리 완료: 사용자 ID - {}", user.getId());
    }

    @Override
    public UserInfo searchUser(User user, String token) {
        log.info("토큰으로 사용자 검색 시작");
        User targetUser = userManager.findByToken(token);
        log.info("사용자 검색 완료: 사용자 ID - {}", user.getId());
        UserInfo userInfo = UserInfo.of(targetUser);

        if (blockUserManager.existsByBlockingUserAndBlockedUser(user, targetUser)) {
            userInfo.setBlocked(true);
        }

        if(blockUserManager.existsByBlockingUserAndBlockedUser(targetUser, user)) {
            throw new UserBlockedException(targetUser.getNickname(), user.getNickname());
        }

        return userInfo;
    }

    @Override
    public void deleteUser(User user) {
        log.info("사용자 계정 삭제 요청: 사용자 ID - {}", user.getId());
        oAuthUnlinkManager.unlinkUser(user);
        OauthUser oauthUser = oauthUserManager.findByEmail(user.getEmail());
        userManager.delete(user);
        oauthUserManager.delete(oauthUser);
        log.info("사용자 계정 삭제 완료: 사용자 ID - {}", user.getId());
    }

    @Override
    public void blockUser(User user, String userToken) {
        log.info("토큰으로 사용자 검색 시작");
        User targetUser = userManager.findByToken(userToken);
        log.info("차단할 사용자 검색 완료: 사용자 ID - {}", user.getId());

        if (!blockUserManager.existsByBlockingUserAndBlockedUser(user, targetUser)) {
            blockUserManager.blockUser(user, targetUser);
        }
    }

    @Override
    public void unblockUser(User user, String userToken) {
        log.info("토큰으로 사용자 검색 시작");
        User targetUser = userManager.findByToken(userToken);
        log.info("차단 해제할 사용자 검색 완료: 사용자 ID - {}", user.getId());

        if (blockUserManager.existsByBlockingUserAndBlockedUser(user, targetUser)) {
            blockUserManager.unblockUser(user, targetUser);
        }
    }

    @Override
    public List<UserInfo> readBlockedUsers(User user) {
        List<User> blockedUsers = blockUserManager.findAllBlockedUser(user);

        return blockedUsers.stream()
                .map(UserInfo::of) // User 객체를 UserInfo 객체로 변환
                .toList();
    }
}
