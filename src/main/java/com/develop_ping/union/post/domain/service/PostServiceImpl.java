package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.dto.command.*;
import com.develop_ping.union.post.domain.dto.info.*;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostManager postManager;

    @Override
    public PostCreationInfo createPost(PostCreationCommand command) {
        log.info("[ CALL: PostService.createPost() ] title: {}",command.getTitle());

        // TODO: token에서 유저 추출하기
        User user = User.builder().id(1L).build();

        // TODO: photo List 저장하기 -> 이거 따로 빼기로 함!!!

        // TODO: thumbnail에 들어갈 사진 압축하기
        String thumbnail = null;
        if (command.getPhotos() != null && !command.getPhotos().isEmpty()) {
            thumbnail = command.getPhotos().get(0);
            // 압축 메소드 호출? (미구현)
        }

        Post post = postManager.save(Post.of(command, user, thumbnail));
        return PostCreationInfo.of(post);
    }

    @Override
    public PostUpdateInfo updatePost(PostUpdateCommand command) {
        log.info("[ CALL: PostService.updatePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());

        // TODO: command.getToken()으로 사용자 권한 확인하는 로직 추가

        post.updateTitle(command.getTitle());
        post.updateContent(command.getContent());

        Post updatedPost = postManager.save(post);

        return PostUpdateInfo.of(updatedPost);
    }

    @Override
    public void deletePost(String token, Long postId) {
        log.info("[ CALL: PostService.deletePost() ] post id: {}", postId);

        Post post = postManager.findById(postId);

        // TODO: command.getToken()으로 사용자 권한 확인하는 로직 추가

        postManager.delete(post);
    }
}
