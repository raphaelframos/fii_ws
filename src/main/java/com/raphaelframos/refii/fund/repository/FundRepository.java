package com.raphaelframos.refii.fund.repository;

import com.raphaelframos.refii.common.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<FundEntity, Long> {

    boolean existsFundsBySymbol(String symbol);
}
