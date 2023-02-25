package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.common.entity.ChatFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatFund, Long> {

    @Query(value = "SELECT * FROM fund_chat c WHERE c.position = ?1 AND c.profile_id = ?2", nativeQuery = true)
    Optional<ChatFund> findByPositionAndUserId(int position, Long userId);

    @Query(value = "SELECT * FROM fund_chat c WHERE c.profile_id = ?1 ORDER BY c.position", nativeQuery = true)
    List<ChatFund> findByUserId(Long userId);

    @Query(value = "DELETE FROM fund_chat f WHERE f.profile_id = ?1", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(Long userId);
}
