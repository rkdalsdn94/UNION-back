package com.develop_ping.union.post.infra;

import com.develop_ping.union.post.domain.Post;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostManagerImpl implements PostManager {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post saveAndFlush(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
