package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.exception.PostPermissionDeniedException;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostManager postManager;

    @Override
    @Transactional
    public PostInfo createPost(PostCommand command) {
        log.info("[ CALL: PostService.createPost() ] title: {}",command.getTitle());

        User user = command.getUser();

        // TODO: thumbnail에 들어갈 사진 압축하기
        String thumbnail = null;
        if (command.getThumbnail() != null) {
            // thumbnail을 compress하는 메소드 호출
            thumbnail = command.getThumbnail();
        }

        Post post = postManager.save(Post.of(command, user, thumbnail));
        return PostInfo.from(post);
    }

    @Override
    @Transactional
    public PostInfo updatePost(PostCommand command) {
        log.info("[ CALL: PostService.updatePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());
        User user = command.getUser();

        validatePostOwner(user, post);

        post.updateTitle(command.getTitle());
        post.updateContent(command.getContent());

        Post updatedPost = postManager.save(post);
        return PostInfo.from(updatedPost);
    }

    @Override
    @Transactional
    public void deletePost(PostCommand command) {
        log.info("[ CALL: PostService.deletePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());
        User user = command.getUser();

        validatePostOwner(user, post);

        postManager.delete(post);
    }

    @Override
    @Transactional
    public PostInfo getPost(PostCommand command) {
        log.info("[ CALL: PostService.getPost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());

        post.incrementViews();
        postManager.save(post);

        return PostInfo.from(post);
    }

    private void validatePostOwner(User user, Post post) {
        if (!user.getId().equals(post.getUser().getId())) {
            throw new PostPermissionDeniedException(user.getId(), post.getId());
        }
    }
}
