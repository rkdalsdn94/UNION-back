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

        commentService.deleteComment(CommentCommand.deletionOf(commentId, user));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDetailResponse> getComment(@PathVariable("commentId") Long commentId) {
        log.info("[ CALL: CommentController.getComment() ] with commentId: {}", commentId);

        CommentInfo info = commentService.getComment(commentId);
        CommentDetailResponse response = CommentDetailResponse.from(info);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<CommentListResponse> getCommentsByPostId(@PathVariable("postId") Long postId) {
        log.info("[ CALL: CommentController.getCommentsByPostId() ] with postId: {}", postId);

        // TODO: info와 response의 변경 스펙에 따라 수정하기
        CommentListInfo listInfo = commentService.getCommentsByPostId(postId);
        CommentListResponse response = CommentListResponse.from(listInfo);

        return ResponseEntity.ok(response);
    }
}
