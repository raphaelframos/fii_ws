package com.raphaelframos.refii.wallet.repository;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import com.raphaelframos.refii.wallet.data.FundFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundWalletRepository extends JpaRepository<FundWalletEntity, Long> {

    @Query(value = "SELECT " +
            "r.symbol AS symbol, SUM(f.amount) AS totalAmount, SUM(f.price) AS totalPrice, r.segment AS segment" +
            " FROM fund_wallet f, fund_scrap r WHERE f.fund_id = r.id AND f.profile_id = ?1 GROUP BY f.fund_id, r.symbol, r.segment", nativeQuery = true)
    List<Object[]> findWalletBy(Long userId);

    @Query(value = "SELECT SUM(f.price) FROM fund_wallet f WHERE f.profile_id = ?1", nativeQuery = true)
    double totalPrice(Long userId);
}
