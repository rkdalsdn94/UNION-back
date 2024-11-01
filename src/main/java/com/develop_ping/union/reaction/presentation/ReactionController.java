package com.develop_ping.union.reaction.presentation;

import com.develop_ping.union.reaction.presentation.dto.PostReactionResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ReactionController {
    @GetMapping("/{postId}/likes")
    public ResponseEntity<PostReactionResponse> getPostLikes(@PathVariable("postId") Long postId,
                                                             @AuthenticationPrincipal User user) {
        log.info("[ CALL: ReactionController.getPostLikes() ] with postId: {}", postId);

        PostReactionResponse response = PostReactionResponse.builder()
            .postLikes(123)
            .liked(false)
            .build();

        return ResponseEntity.ok(response);
    }
}
