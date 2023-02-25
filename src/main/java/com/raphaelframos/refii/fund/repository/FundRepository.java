package com.raphaelframos.refii.fund.repository;

import com.raphaelframos.refii.common.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {

    boolean existsFundsBySymbol(String symbol);

    @Query(value = "SELECT (symbol) FROM FUND", nativeQuery = true)
    List<String> findNames();

    Fund findBySymbol(String value);
}
