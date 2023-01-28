package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.common.entity.ChatFundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatFundEntity, Long> {

    @Query("FROM FUND_CHAT c JOIN FETCH c.profile p WHERE c.position = ?1 AND p.id = ?2")
    Optional<ChatFundEntity> findByPositionAndUserId(int position, Long userId);

    @Query("FROM FUND_CHAT c JOIN FETCH c.profile p WHERE p.id = ?1 ORDER BY c.position")
    List<ChatFundEntity> findByUserId(Long userId);

    @Query(value = "DELETE FROM FUND_CHAT f WHERE f.profile_id = ?1", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(Long userId);
}
