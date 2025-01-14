package org.dmit3ii.limitcontrolmicroservice.service;

import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactionsInThisMonth(Long accountFrom, ExpenseCategory expenseCategory);

    List<Transaction> getAllTransactionsInThisMonthWithLimitsExceeded(Long accountFrom);
}
