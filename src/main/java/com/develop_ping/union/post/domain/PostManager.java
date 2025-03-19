package com.develop_ping.union.post.domain;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostManager {
    Post saveAndFlush(Post post);
    Post findById(Long id);
    Post save(Post post);
    void delete(Post post);
    Page<Post> findByPostType(PostType postType, Pageable pageable, List<Long> blockedUserIds);
    Page<Post> findByUser(User user, Pageable pageable, List<Long> blockedUserIds);
    Page<Post> findByUserComments(User user, Pageable pageable, List<Long> blockedUserIds);
    Post validatePostOwner(Long userId, Long postId);
    Page<Post> searchByTypeAndKeyword(PostType type, String keyword, Pageable pageable, List<Long> blockedUserIds);
    Page<Post> searchByKeyword(String keyword, Pageable pageable, List<Long> blockedUserIds);
    Page<Post> findPopularPosts(Pageable pageable, List<Long> blockedUserIds);
    long countByUserId(Long userId);
    long countPostsByUserComments(Long userId, List<Long> blockedUserIds);
}
