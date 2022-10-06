package com.raphaelframos.refii.wallet.repository;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundWalletRepository extends JpaRepository<FundWalletEntity, Long> {
}
