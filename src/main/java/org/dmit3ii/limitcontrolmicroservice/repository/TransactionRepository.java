package org.dmit3ii.limitcontrolmicroservice.repository;

import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query ("select t from Transaction t where t.accountFrom= :accountFrom and t.expenseCategory= :expenseCategory and  EXTRACT(MONTH FROM t.datetime) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM t.datetime) = EXTRACT(YEAR FROM CURRENT_DATE)")
    List<Transaction> findAllByAccountFromAndExpenseCategoryAndThisMonth(@Param("accountFrom")Long accountFrom, @Param("expenseCategory") ExpenseCategory expenseCategory);
}
