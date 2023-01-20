package com.raphaelframos.refii.wallet.repository;

import com.raphaelframos.refii.common.entity.NewFundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewFundRepository extends JpaRepository<NewFundEntity, Long> {}
