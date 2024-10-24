package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.dto.command.PostCreationCommand;
import com.develop_ping.union.post.domain.dto.command.PostUpdateCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.exception.PostPermissionDeniedException;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostManager postManager;
    private final UserManager userManager;

    @Override
    public PostInfo createPost(PostCreationCommand command) {
        log.info("[ CALL: PostService.createPost() ] title: {}",command.getTitle());

        User user = userManager.findByToken(command.getToken());
//        User user = userManager.findById(1L);

        // TODO: thumbnail에 들어갈 사진 압축하기
        String thumbnail = null;
        if (command.getThumbnail() != null) {
            // thumbnail을 compress하는 메소드 호출
        }

        Post post = postManager.save(Post.of(command, user, thumbnail));
        return PostInfo.of(post);
    }

    @Override
    public PostInfo updatePost(PostUpdateCommand command) {
        log.info("[ CALL: PostService.updatePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());
        User user = userManager.findByToken(command.getToken());
//        User user = userManager.findById(1L);

        validatePostOwner(user, post);

        post.updateTitle(command.getTitle());
        post.updateContent(command.getContent());

        Post updatedPost = postManager.save(post);
        return PostInfo.of(updatedPost);
    }

    @Override
    public void deletePost(String token, Long postId) {
        log.info("[ CALL: PostService.deletePost() ] post id: {}", postId);

        Post post = postManager.findById(postId);
        User user = userManager.findByToken(token);
//        User user = userManager.findById(1L);

        validatePostOwner(user, post);

        postManager.delete(post);
    }

    @Override
    public PostInfo getPost(Long postId) {
        log.info("[ CALL: PostService.getPost() ] post id: {}", postId);

        Post post = postManager.findById(postId);

        post.incrementViews();
        postManager.save(post);

        return PostInfo.of(post);
    }

    private void validatePostOwner(User user, Post post) {
        if (!user.equals(post.getUser())) {
            throw new PostPermissionDeniedException(user.getId(), post.getId());
        }
    }
}
