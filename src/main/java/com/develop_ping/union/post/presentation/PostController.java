package com.develop_ping.union.post.presentation;

import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.dto.info.PostReactionInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.post.domain.service.PostService;
import com.develop_ping.union.post.presentation.dto.request.*;
import com.develop_ping.union.post.presentation.dto.response.*;
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


        PostCommand command = request.toCommand(user, type);
        PostInfo info = postService.createPost(command);
        PostCreationResponse response = PostCreationResponse.from(info);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{boardType}/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable("boardType") PostType type,
                                                      @PathVariable("postId") Long postId,
                                                      @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.getPost() ] with postId: {}", postId);

        PostCommand command = PostCommand.of(user, postId);
        PostInfo info = postService.getPost(command);
        PostDetailResponse response = PostDetailResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{boardType}/{postId}")
    public ResponseEntity<PostUpdateResponse> updatePost(@PathVariable("boardType") PostType type,
                                                         @PathVariable("postId") Long postId,
                                                         @RequestBody PostUpdateRequest request,
                                                         @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.updatePost() ] with postId: {}", postId);
        log.info("[ USER ID: {} ]", user.getId());

        PostCommand command = request.toCommand(user, postId);
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

        postService.deletePost(PostCommand.of(user, postId));

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<Boolean> likePost(@PathVariable("postId") Long postId,
                                            @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.likePost() ] with postId: {}", postId);
        log.info("[ USER ID: {} ]", user.getId());

        return ResponseEntity.ok(postService.likePost(PostCommand.of(user, postId)));
    }

    @GetMapping("/likes/{postId}")
    public ResponseEntity<PostReactionResponse> getPostLikes(@PathVariable("postId") Long postId,
                                                             @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.getPostLikes() ] with postId: {}", postId);

        PostReactionInfo info = postService.getPostLikes(PostCommand.of(user, postId));
        PostReactionResponse response = PostReactionResponse.from(info);

        return ResponseEntity.ok(response);
    }
}
