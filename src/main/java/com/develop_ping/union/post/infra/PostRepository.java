package com.develop_ping.union.post.infra;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            SELECT p FROM Post p
            WHERE p.type = :type
                AND p.user.id NOT IN :blockedUserIds
    """)
    Page<Post> findByType(PostType type,
                          @Param("blockedUserIds") List<Long> blockedUserIds,
                          Pageable pageable);

    @Query("""
            SELECT p FROM Post p
            WHERE p.type = :type
                AND p.user.id NOT IN :blockedUserIds
                AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<Post> searchByTypeAndKeyword(
            @Param("type") PostType type,
            @Param("keyword") String keyword,
            @Param("blockedUserIds") List<Long> blockedUserIds,
            Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);

    @Query("""
            SELECT DISTINCT p FROM Post p
            JOIN p.comments c
            WHERE c.user = :user
                AND p.user.id NOT IN :blockedUserIds
    """)
    Page<Post> findPostsByUserComments(@Param("user") User user,
                                       @Param("blockedUserIds") List<Long> blockedUserIds,
                                       Pageable pageable);

    @Query("""
            SELECT p FROM Post p
            WHERE p.user.id NOT IN :blockedUserIds
                AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<Post> searchByKeyword(@Param("keyword") String keyword,
                               @Param("blockedUserIds") List<Long> blockedUserIds,
                               Pageable pageable);

    @Query("""
            SELECT p FROM Post p
            JOIN Reaction r ON p.id = r.targetId
            WHERE p.user.id NOT IN :blockedUserIds
                AND r.type = :reactionType
            GROUP BY p.id
            HAVING COUNT(r.id) >= :minLikes
    """)
    Page<Post> findPopularPosts(@Param("reactionType") ReactionType reactionType,
                                @Param("minLikes") long minLikes,
                                @Param("blockedUserIds") List<Long> blockedUserIds,
                                Pageable pageable);

    long countByUserId(Long userId);

    @Query("""
            SELECT COUNT(DISTINCT p)
            FROM Post p JOIN p.comments c
            WHERE c.user.id = :userId
                AND p.user.id NOT IN :blockedUserIds
    """)
    long countPostsByUserComments(@Param("userId") Long userId,
                                  @Param("blockedUserIds") List<Long> blockedUserIds);
}
