package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private LimitService limitService;
    private TransactionRepository transactionRepository;
    private ExchangesRateService exchangesRateService;


    @Override
    public Transaction saveTransaction(Transaction transaction) {
        Limit lastLimit = limitService.getLastLimit(transaction.getAccountFrom(), transaction.getExpenseCategory());
        transaction.setLimit(lastLimit);
        BigDecimal lastLimitSum = lastLimit.getLimitSum();
        List<Transaction> transactionList = getAllTransactionsInThisMonth(transaction.getAccountFrom(), transaction.getExpenseCategory());
        ExchangeRates exchangeRates = exchangesRateService.getLastExchangeRates();
        BigDecimal sum = calculateTotalSumInUSD(transactionList, exchangeRates).add(transaction.getSum());
        if (sum.compareTo(lastLimitSum) > 0) {
            transaction.setLimitExceeded(true);
        }
        return transactionRepository.save(transaction);
    }

    /**
     * Метод для подсчета итоговой суммы транзакций в USD
     *
     * @param transactions
     * @param exchangeRates
     * @return
     */
    private BigDecimal calculateTotalSumInUSD(List<Transaction> transactions, ExchangeRates exchangeRates) {
        return transactions.stream()
                .map(x -> exchangesRateService.convertSumToUSD(exchangeRates, x))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Получение всех транзакций в заданной категории за месяц
     *
     * @param accountFrom
     * @param expenseCategory
     * @return
     */
    @Override
    public List<Transaction> getAllTransactionsInThisMonth(Long accountFrom, ExpenseCategory expenseCategory) {
        return transactionRepository.findAllByAccountFromAndExpenseCategoryAndThisMonth(accountFrom, expenseCategory);
    }
}
