package com.develop_ping.union.user.presentation;

import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.service.UserService;
import com.develop_ping.union.user.presentation.dto.request.RegisterRequest;
import com.develop_ping.union.user.presentation.dto.request.UpdateRequest;
import com.develop_ping.union.user.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody RegisterRequest request) {
        log.info("유저 생성 요청 받음 : {}", request.getNickname());
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
        log.info("유저 정보 업데이트 요청 받음 : {}", request.getNickname());
        UserInfo userInfo = userService.updateUser(request.toCommand(), user);
        UserResponse response = new UserResponse(userInfo);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signOutUser (@AuthenticationPrincipal User user) {
        log.info("로그아웃 요청 확인 : {}", user.getNickname());
        userService.signOut(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userToken}")
    public ResponseEntity<UserResponse> searchUser(@PathVariable String userToken) {
        log.info("유저 상세 조회 요청 확인");
        UserInfo userInfo = userService.searchUser(userToken);
        UserResponse response = new UserResponse(userInfo);

        return ResponseEntity.ok()
                .body(response);
    }
}
