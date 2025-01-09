package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private LimitService limitService;


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        BigDecimal lastLimit = limitService.getLastLimit(transaction.getAccountTo(), transaction.getExpenseCategory());

        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsInThisMonth() {
        return List.of();
    }
}
