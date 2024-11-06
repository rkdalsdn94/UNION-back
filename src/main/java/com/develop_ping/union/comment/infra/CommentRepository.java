package com.develop_ping.union.comment.infra;

import com.develop_ping.union.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndParentIsNull(Long postId);
    long countByPostId(Long postId);
    List<Comment> findByPostId(Long postId);


    @Query(value = """
        SELECT c.* FROM comments c
        LEFT JOIN reactions r ON c.id = r.target_id AND r.type = :reactionType
        WHERE c.post_id = :postId
        GROUP BY c.id
        ORDER BY COUNT(r.id) DESC, c.created_at ASC
        LIMIT 1
        """, nativeQuery = true)
    Optional<Comment> findTopByPostIdAndReactionType(@Param("postId") Long postId,
                                                     @Param("reactionType") String reactionType);
}
