package com.develop_ping.union.comment.presentation;
import com.develop_ping.union.comment.domain.dto.*;
import com.develop_ping.union.comment.domain.service.CommentService;
import com.develop_ping.union.comment.presentation.dto.request.*;
import com.develop_ping.union.comment.presentation.dto.response.*;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommentCreationResponse> createComment(@RequestBody CommentCreationRequest request,
                                                                 @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.createComment() ] user id: {}", user.getId());

        CommentCommand command = request.toCommand(user);
        CommentInfo info = commentService.createComment(command);
        CommentCreationResponse response = CommentCreationResponse.from(info);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentUpdateResponse> updateComment(@PathVariable("commentId") Long commentId,
                                                               @RequestBody CommentUpdateRequest request,
                                                               @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.updateComment() ] with commentId: {}", commentId);

        CommentCommand command = request.toCommand(commentId, user);
        CommentInfo info = commentService.updateComment(command);
        CommentUpdateResponse response = CommentUpdateResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId,
                                              @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.deleteComment() ] with commentId: {}", commentId);

        commentService.deleteComment(CommentCommand.of(commentId, user));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<CommentListResponse> getCommentsByPostId(@PathVariable("postId") Long postId,
                                                                   @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.getCommentsByPostId() ] with postId: {}", postId);

        CommentCommand command = CommentCommand.getOf(postId, user);
        List<CommentInfo> listInfo = commentService.getCommentsByPostId(command);
        CommentListResponse response = CommentListResponse.from(listInfo);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment/like/{commentId}")
    public ResponseEntity<Boolean> likeComment(@PathVariable("commentId") Long commentId,
                                               @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.likeComment() ] with commentId: {}", commentId);

        return ResponseEntity.ok(commentService.likeComment(CommentCommand.of(commentId, user)));
    }

    @GetMapping("/comment/best/{postId}")
    public ResponseEntity<CommentBestResponse> getBestComment(@PathVariable("postId") Long postId,
                                                                @AuthenticationPrincipal User user) {
        log.info("[ CALL: CommentController.getBestComment() ] with postId: {}", postId);

        CommentCommand command = CommentCommand.getOf(postId, user);
        CommentInfo info = commentService.getBestComment(command);
        CommentBestResponse response = CommentBestResponse.from(info);

        return ResponseEntity.ok(response);
    }
}
