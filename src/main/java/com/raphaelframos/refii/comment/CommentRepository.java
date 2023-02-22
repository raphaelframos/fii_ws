package com.raphaelframos.refii.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "FROM COMMENT c JOIN FETCH c.profile p ON p.id = c.profile.id JOIN FETCH c.fund f ON p.fund.id = f.id WHERE p.id = ?1 AND f.id = ?2 ORDER BY c.date", nativeQuery = true)
    List<Comment> findMyComments(Long userId, Long fundId);

    @Query(value = "FROM COMMENT c JOIN FETCH c.fund f ON p.fund.id = f.id WHERE f.id = ?1 ORDER BY c.date", nativeQuery = true)
    List<Comment> find(Long fundId);

    @Query(value = "FROM COMMENT c JOIN FETCH c.profile p ON p.id = c.profile.id JOIN FETCH c.fund f ON p.fund.id = f.id WHERE p.id = ?1 AND f.id = ?2 ORDER BY c.date DESC LIMIT 1", nativeQuery = true)
    Optional<Comment> lastComment(Long userId, Long fundId);
}
