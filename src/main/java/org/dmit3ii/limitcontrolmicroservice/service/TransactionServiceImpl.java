package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        boolean isLimitExceeded = checkLimitExceeded(transaction, lastLimit);
        transaction.setLimitExceeded(isLimitExceeded);
        return transactionRepository.save(transaction);
    }

    /**
     * Метод проверки превышения заданного лимита
     *
     * @param transaction
     * @param lastLimit
     * @return
     */
    private boolean checkLimitExceeded(Transaction transaction, Limit lastLimit) {
        BigDecimal lastLimitSum = lastLimit.getLimitSum();
        List<Transaction> transactionList = getAllTransactionsInThisMonth(transaction.getAccountFrom(), transaction.getExpenseCategory());
        ExchangeRates exchangeRates = exchangesRateService.getLastExchangeRates();
        BigDecimal sum = calculateTotalSumInUSD(transactionList, exchangeRates).add(exchangesRateService.convertSumToUSD(exchangeRates, transaction));
        return sum.compareTo(lastLimitSum) > 0;
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
                .map(transaction -> exchangesRateService.convertSumToUSD(exchangeRates, transaction))
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

    @Override
    public List<Transaction> getAllTransactionsInThisMonthWithLimitsExceeded(Long accountFrom) {
        List<Transaction> transactionListExceeded = new ArrayList<>();
        for (ExpenseCategory expenseCategory : ExpenseCategory.values()) {
            transactionListExceeded.addAll(getAllTransactionsInThisMonth(accountFrom, expenseCategory).stream().filter(Transaction::isLimitExceeded).collect(Collectors.toList()));
        }
        return transactionListExceeded;
    }
}
