package com.develop_ping.union.post.presentation;

import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.command.PostListCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.dto.info.PostListInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.post.domain.service.PostService;
import com.develop_ping.union.post.presentation.dto.request.*;
import com.develop_ping.union.post.presentation.dto.response.*;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/board/{boardType}")
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

    @PutMapping("/board/{boardType}/{postId}")
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

    @DeleteMapping("/board/{boardType}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("boardType") PostType type,
                                           @PathVariable("postId") Long postId,
                                           @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.deletePost() ] with postId: {}", postId);
        log.info("[ USER ID: {} ]", user.getId());

        postService.deletePost(PostCommand.of(user, postId));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/board/{boardType}/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable("boardType") PostType type,
                                                      @PathVariable("postId") Long postId,
                                                      @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.getPost() ] with postId: {}", postId);

        PostCommand command = PostCommand.of(user, postId);
        PostInfo info = postService.getPost(command);
        PostDetailResponse response = PostDetailResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/board/{boardType}")
    public Page<PostListResponse> getPostList(@PathVariable("boardType") PostType type,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "3") int size,
                                              @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostController.getPostList() ]");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        log.info("[ Pageable successfully created ]");

        PostListCommand command = PostListCommand.of(user, type, pageable);
        Page<PostListInfo> postListInfoPage = postService.getPostsByPage(command);

        return postListInfoPage.map(PostListResponse::from);
    }
}
