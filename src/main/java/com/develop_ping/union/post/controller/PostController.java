package com.develop_ping.union.post.controller;

import com.develop_ping.union.post.application.PostService;
import com.develop_ping.union.post.application.dto.command.*;
import com.develop_ping.union.post.controller.dto.request.*;
import com.develop_ping.union.post.controller.dto.response.*;
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

        log.info("type: {}, token: {}", type, token);

        PostCreationCommand command = request.toCommand(token, type);
        System.out.println("command.getType: " + command.getType()+", command.getTitle: "+command.getTitle()+", command.getContent: "+command.getContent());
        PostCreationResponse response = postService.createPost(command).toResponse();

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
        PostUpdateResponse response = postService.updatePost(command).toResponse();

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
