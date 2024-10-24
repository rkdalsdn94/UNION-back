package com.develop_ping.union.user.presentation;

import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.service.UserService;
import com.develop_ping.union.user.presentation.dto.request.RegisterRequest;
import com.develop_ping.union.user.presentation.dto.response.UserDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDetailResponse> signUp(@Valid @RequestBody RegisterRequest request) {
        log.info("유저 생성 요청 받음.");
        UserInfo userInfo = userService.signUp(request.toCommand());
        UserDetailResponse response = new UserDetailResponse(userInfo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userInfo.getAccessToken())
                .header("Refresh-Token", userInfo.getRefreshToken())
                .body(response);
    }
}
