package org.dmit3ii.limitcontrolmicroservice.repository;

import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
