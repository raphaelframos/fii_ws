package com.raphaelframos.refii.wallet.repository;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundWalletRepository extends JpaRepository<FundWalletEntity, Long> {

    @Query(value = "SELECT " +
            "r.symbol AS symbol, SUM(f.amount) AS totalAmount, SUM(f.price) AS totalPrice, r.segment AS segment, r.id AS fundScrapId" +
            " FROM WALLET f, FUND r WHERE f.fund_id = r.id AND f.profile_id = ?1 GROUP BY f.fund_id, r.symbol, r.segment, r.id", nativeQuery = true)
    List<Object[]> findWalletBy(Long userId);

    @Query(value = "SELECT SUM(f.price) FROM WALLET f WHERE f.profile_id = ?1", nativeQuery = true)
    double totalPrice(Long userId);

    @Query(value = "SELECT * FROM WALLET f WHERE f.fund_id = ?1 AND f.profile_id = ?2", nativeQuery = true)
    List<FundWalletEntity> findByIdAndUser(Long fundId, Long userId);
}
