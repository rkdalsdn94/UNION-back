package com.develop_ping.union.user.presentation;

import com.develop_ping.union.user.domain.service.UserService;
import com.develop_ping.union.user.presentation.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public ResponseEntity<Void> signUp(@RequestBody RegisterRequest request) {
        log.info("유저 생성 요청 받음.");
        userService.signUp(request.toCommand());

        return null;
    }
}
