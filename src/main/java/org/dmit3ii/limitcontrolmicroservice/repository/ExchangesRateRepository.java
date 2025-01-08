package org.dmit3ii.limitcontrolmicroservice.repository;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangesRateRepository extends JpaRepository<ExchangeRates, Long> {
    @Query("select e from ExchangeRates e order by e.timestamp limit 1")
    ExchangeRates findLast();
}
