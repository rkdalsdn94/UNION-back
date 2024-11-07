package com.develop_ping.union.post.infra;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.post.exception.PostNotFoundException;
import com.develop_ping.union.post.exception.PostPermissionDeniedException;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostManagerImpl implements PostManager {
    private final PostRepository postRepository;

    private final static long POPULAR_POST_REACTION_COUNT = 2;

    @Override
    public Post saveAndFlush(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Page<Post> findByPostType(PostType postType, Pageable pageable) {
        log.info("[ CALL: PostManager.findByPostType() ] postType: {}", postType);
        return postRepository.findByType(postType, pageable);
    }

    @Override
    public Page<Post> findByUser(User user, Pageable pageable) {
        return postRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Post> findByUserComments(User user, Pageable pageable) {
        return postRepository.findPostsByUserComments(user, pageable);
    }

    @Override
    public Post validatePostOwner(Long userId, Long postId) {
        Post post = findById(postId);

        if (!userId.equals(post.getUser().getId())) {
            throw new PostPermissionDeniedException(userId, post.getId());
        }

        return post;
    }

    @Override
    public Page<Post> searchByTypeAndKeyword(PostType type, String keyword, Pageable pageable) {
        return postRepository.searchByTypeAndKeyword(type, keyword, pageable);
    }

    @Override
    public Page<Post> searchByKeyword(String keyword, Pageable pageable) {
        return  postRepository.searchByKeyword(keyword, pageable);
    }

    @Override
    public Page<Post> findPopularPosts(Pageable pageable) {
        return postRepository.findPopularPosts(ReactionType.POST, POPULAR_POST_REACTION_COUNT, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countPostsByUserComments(Long userId) {
        return postRepository.countPostsByUserComments(userId);
    }
}
