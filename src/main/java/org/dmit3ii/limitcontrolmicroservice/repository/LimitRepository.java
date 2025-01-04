package org.dmit3ii.limitcontrolmicroservice.repository;

import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {
    @Query("SELECT l from Limit l where l.accountTo= :accountTo and l.expenseCategory= :expenseCategory and " +
            "EXTRACT(YEAR FROM l.limitDatetime) = EXTRACT(YEAR FROM CURRENT_DATE) AND " +
            "EXTRACT(MONTH FROM l.limitDatetime) = EXTRACT(MONTH FROM CURRENT_DATE) ORDER BY l.limitDatetime DESC ")
    public List<Limit> findAllByAccountToAndExpenseCategoryAndCurrentMonth(@Param("accountTo") long accountTo, @Param("expenseCategory") ExpenseCategory expenseCategory);
}
