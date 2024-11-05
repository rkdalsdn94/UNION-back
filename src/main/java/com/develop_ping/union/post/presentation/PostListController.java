package com.develop_ping.union.post.presentation;

import com.develop_ping.union.post.domain.Criterion;
import com.develop_ping.union.post.domain.dto.command.PostListCommand;
import com.develop_ping.union.post.domain.dto.info.PostListInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.post.domain.service.PostService;
import com.develop_ping.union.post.presentation.dto.response.PostListResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostListController {
    private final PostService postService;

    @GetMapping("/board/{boardType}")
    public Page<PostListResponse> getPostList(@PathVariable("boardType") PostType type,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "3") int size,
                                              @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostListController.getPostList() ]");

        // 게시판을 기준으로 조회
        Criterion criterion = Criterion.BOARD;

        PostListCommand command = PostListCommand.boardOf(user, type, page, size, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/user/my/posts")
    public Page<PostListResponse> getMyPostList(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size,
                                                @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostListController.getMyPostList() ]");

        // 사용자 기준으로 조회
        Criterion criterion = Criterion.MY;

        PostListCommand command = PostListCommand.of(user, page, size, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/user/my/comments")
    public Page<PostListResponse> getMyCommentedPostList(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size,
                                                         @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostListController.getMyCommentedPostList() ]");

        // 사용자가 댓글을 단 게시글 조회
        Criterion criterion = Criterion.MY_COMMENT;

        PostListCommand command = PostListCommand.of(user, page, size, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/user/{userToken}/posts")
    public Page<PostListResponse> getUserPostList(@PathVariable("userToken") String userToken,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size) {
        log.info("[ CALL: PostListController.getUserPostList() ]");

        // 특정 유저 기준으로 조회
        Criterion criterion = Criterion.USER;

        PostListCommand command = PostListCommand.userOf(page, size, userToken, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/user/{userToken}/comments")
    public Page<PostListResponse> getUserCommentedPostList(@PathVariable("userToken") String userToken,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        log.info("[ CALL: PostListController.getUserCommentedPostList() ]");

        // 특정 유저가 댓글을 단 게시글 조회
        Criterion criterion = Criterion.USER_COMMENT;

        PostListCommand command = PostListCommand.userOf(page, size, userToken, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/board/search/{boardType}")
    public Page<PostListResponse> searchPostList(@PathVariable("boardType") PostType type,
                                                 @RequestParam("keyword") String keyword,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size,
                                                 @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostListController.searchPostList() ]");

        // 검색 키워드를 기준으로 조회
        Criterion criterion = Criterion.BOARD_SEARCH;

        PostListCommand command = PostListCommand.searchOf(user, type, keyword, page, size, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }

    @GetMapping("/board/home")
    public Page<PostListResponse> getHomePostList(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size,
                                                  @AuthenticationPrincipal User user) {
        log.info("[ CALL: PostListController.getHomePostList() ]");

        // 홈 화면(인기글) 기준으로 조회
        Criterion criterion = Criterion.HOME;

        PostListCommand command = PostListCommand.of(user, page, size, criterion);
        Page<PostListInfo> postListInfoPage = postService.getPosts(command);

        return postListInfoPage.map(PostListResponse::from);
    }
}
