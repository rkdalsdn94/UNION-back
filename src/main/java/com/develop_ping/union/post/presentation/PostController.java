package com.develop_ping.union.post.presentation;

import com.develop_ping.union.post.domain.dto.info.PostCreationInfo;
import com.develop_ping.union.post.domain.dto.info.PostUpdateInfo;
import com.develop_ping.union.post.domain.service.PostService;
import com.develop_ping.union.post.domain.dto.command.*;
import com.develop_ping.union.post.presentation.dto.request.*;
import com.develop_ping.union.post.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @PostMapping("/{boardType}")
    public ResponseEntity<PostCreationResponse> createPost(@PathVariable("boardType") String type,
                                                           @RequestBody PostCreateRequest request) {
        log.info("[ CALL: PostController.createPost() ]");

        // TODO: @RequestHeader("Authorization")로 토큰 받기
        String token = "token";

        PostCreationCommand command = request.toCommand(token, type);
        PostCreationInfo info = postService.createPost(command);
        PostCreationResponse response = PostCreationResponse.from(info);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{boardType}/{postId}")
    public ResponseEntity<PostUpdateResponse> updatePost(@PathVariable("boardType") String type,
                                                         @PathVariable("postId") Long postId,
                                                         @RequestBody PostUpdateRequest request) {
        log.info("[ CALL: PostController.updatePost() ]");

        // TODO: @RequestHeader("Authorization")로 토큰 받기
        String token = "token";

        PostUpdateCommand command = request.toCommand(token, postId);
        PostUpdateInfo info = postService.updatePost(command);
        PostUpdateResponse response = PostUpdateResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{boardType}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("boardType") String type,
                                           @PathVariable("postId") Long postId) {
        log.info("[ CALL: PostController.deletePost() ]");

        // TODO: @RequestHeader("Authorization")로 토큰 받기
        String token = "token";

        postService.deletePost(token, postId);

        return ResponseEntity.noContent().build();
    }
}
