package com.raphaelframos.refii.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM COMMENT c WHERE c.profile_id = ?1 AND c.fund_id = ?2 ORDER BY c.id DESC", nativeQuery = true)
    List<Comment> findMyComments(Long userId, Long fundId);

    @Query(value = "SELECT * FROM COMMENT c WHERE c.fund_id = ?1 ORDER BY c.id DESC", nativeQuery = true)
    List<Comment> find(Long fundId);

    @Query(value = "SELECT * FROM COMMENT c WHERE c.profile_id = ?1 AND c.fund_id = ?2 ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
    Optional<Comment> lastComment(Long userId, Long fundId);
}
