package com.develop_ping.union.post.presentation;

import com.develop_ping.union.post.domain.dto.command.PostCreationCommand;
import com.develop_ping.union.post.domain.dto.command.PostDeleteCommand;
import com.develop_ping.union.post.domain.dto.command.PostUpdateCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.post.domain.service.PostService;
import com.develop_ping.union.post.presentation.dto.request.PostCreateRequest;
import com.develop_ping.union.post.presentation.dto.request.PostUpdateRequest;
import com.develop_ping.union.post.presentation.dto.response.PostCreationResponse;
import com.develop_ping.union.post.presentation.dto.response.PostDetailResponse;
import com.develop_ping.union.post.presentation.dto.response.PostUpdateResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @PostMapping("/{boardType}")
    public ResponseEntity<PostCreationResponse> createPost(@PathVariable("boardType") PostType type,
                                                           @RequestBody PostCreateRequest request,
                                                           @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.createPost() ]");
        log.info("[ USER ID: {} ]", user.getId());

        PostCreationCommand command = request.toCommand(user, type);
        PostInfo info = postService.createPost(command);
        PostCreationResponse response = PostCreationResponse.from(info);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{boardType}/{postId}")
    public ResponseEntity<PostUpdateResponse> updatePost(@PathVariable("boardType") PostType type,
                                                         @PathVariable("postId") Long postId,
                                                         @RequestBody PostUpdateRequest request,
                                                         @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.updatePost() ] with postId: {}", postId);
        log.info("[ USER ID: {} ]", user.getId());

        PostUpdateCommand command = request.toCommand(user, postId);
        PostInfo info = postService.updatePost(command);
        PostUpdateResponse response = PostUpdateResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{boardType}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("boardType") PostType type,
                                           @PathVariable("postId") Long postId,
                                           @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.deletePost() ] with postId: {}", postId);
        log.info("[ USER ID: {} ]", user.getId());

        postService.deletePost(PostDeleteCommand.of(postId, user));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardType}/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable("boardType") PostType type,
                                                      @PathVariable("postId") Long postId) {
        log.info("[ CALL: PostController.getPost() ] with postId: {}", postId);

        PostInfo info = postService.getPost(postId);
        PostDetailResponse response = PostDetailResponse.from(info);

        return ResponseEntity.ok(response);
    }
}
