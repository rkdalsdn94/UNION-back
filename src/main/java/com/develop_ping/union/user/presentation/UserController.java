package com.develop_ping.union.user.presentation;

import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.service.UserService;
import com.develop_ping.union.user.presentation.dto.request.RegisterRequest;
import com.develop_ping.union.user.presentation.dto.request.UpdateRequest;
import com.develop_ping.union.user.presentation.dto.response.OtherUserResponse;
import com.develop_ping.union.user.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody RegisterRequest request) {
        log.info("회원 가입 요청 받음: 닉네임 - {}", request.getNickname());
        UserInfo userInfo = userService.signUp(request.toCommand());
        UserResponse response = new UserResponse(userInfo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userInfo.getAccessToken())
                .header("Refresh-Token", userInfo.getRefreshToken())
                .body(response);
    }

    @PutMapping("/my")
    public ResponseEntity<UserResponse> updateUser (@Valid @RequestBody UpdateRequest request,
                                                    @AuthenticationPrincipal User user) {
        log.info("회원 정보 업데이트 요청 받음: 닉네임 - {}", request.getNickname());
        UserInfo userInfo = userService.updateUser(request.toCommand(), user);
        UserResponse response = new UserResponse(userInfo);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signOutUser (@AuthenticationPrincipal User user) {
        log.info("로그아웃 요청 확인: 닉네임 - {}", user.getNickname());
        userService.signOut(user);
        log.info("로그아웃 완료: 닉네임 - {}", user.getNickname());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userToken}")
    public ResponseEntity<OtherUserResponse> searchUser(@AuthenticationPrincipal User user, @PathVariable String userToken) {
        log.info("회원 상세 조회 요청 확인: 토큰 - {}", userToken);
        UserInfo userInfo = userService.searchUser(user, userToken);
        OtherUserResponse response = new OtherUserResponse(userInfo);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal User user) {
        log.info("회원 삭제 요청 확인: 닉네임 - {}", user.getNickname());
        userService.deleteUser(user);

        log.info("회원 삭제 완료: 닉네임 - {}", user.getNickname());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/block/{userToken}")
    public ResponseEntity<Void> blockUser (@AuthenticationPrincipal User user, @PathVariable String userToken) {
        userService.blockUser(user, userToken);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/block/{userToken}")
    public ResponseEntity<Void> unblockUser (@AuthenticationPrincipal User user, @PathVariable String userToken) {
        userService.unblockUser(user, userToken);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/block")
    public ResponseEntity<List<OtherUserResponse>> readBlockedUsers (@AuthenticationPrincipal User user) {
        List<UserInfo> blockedUsers = userService.readBlockedUsers(user);

        List<OtherUserResponse> responseList = blockedUsers.stream()
                .map(OtherUserResponse::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }
}
