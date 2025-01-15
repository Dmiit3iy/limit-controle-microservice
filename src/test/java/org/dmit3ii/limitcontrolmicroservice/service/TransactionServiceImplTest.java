package org.dmit3ii.limitcontrolmicroservice.service;

import org.dmit3ii.limitcontrolmicroservice.model.*;
import org.dmit3ii.limitcontrolmicroservice.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private LimitService limitService;
    @Mock
    private ExchangesRateService exchangesRateService;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionServiceImpl transactionService;

    private static Object answer(InvocationOnMock invocation) {
        return invocation.getArgument(0);
    }

    @Test
    @DisplayName("Тест проверяет что лимит не превышен")
    void checkLimitExceededSoulReturnFalse() {
        Transaction transaction = createTransaction(BigDecimal.valueOf(50));
        Limit limit = createLimit(BigDecimal.valueOf(10));
        List<Transaction> transactionList = List.of(
                createTransaction(BigDecimal.valueOf(500)),
                createTransaction(BigDecimal.valueOf(400))
        );

        Mockito.when(limitService.getLastLimit(Mockito.anyLong(), Mockito.any(ExpenseCategory.class))).thenReturn(limit);
        Mockito.when(transactionService.getAllTransactionsInThisMonth(Mockito.anyLong(), Mockito.any(ExpenseCategory.class))).thenReturn(transactionList);

        Mockito.when(exchangesRateService.getLastExchangeRates()).thenReturn(createExchangeRates());
        Mockito.when(exchangesRateService.convertSumToUSD(Mockito.any(ExchangeRates.class), Mockito.any(Transaction.class))).thenAnswer(invocation -> {
            Transaction transaction2 = invocation.getArgument(1);
            return transaction2.getSum().divide(BigDecimal.valueOf(102), 6, RoundingMode.HALF_UP);
        });

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(TransactionServiceImplTest::answer);

        Transaction newTransaction = transactionService.saveTransaction(transaction);
        Assertions.assertFalse(newTransaction.isLimitExceeded());

    }

    @Test
    @DisplayName("Тест проверяет что лимит превышен")
    void checkLimitExceededSoulReturnTrue() {
        Transaction transaction = createTransaction(BigDecimal.valueOf(50));
        Limit limit = createLimit(BigDecimal.valueOf(1));
        List<Transaction> transactionList = List.of(
                createTransaction(BigDecimal.valueOf(500)),
                createTransaction(BigDecimal.valueOf(400))
        );

        Mockito.when(limitService.getLastLimit(Mockito.anyLong(), Mockito.any(ExpenseCategory.class))).thenReturn(limit);
        Mockito.when(transactionService.getAllTransactionsInThisMonth(Mockito.anyLong(), Mockito.any(ExpenseCategory.class))).thenReturn(transactionList);

        Mockito.when(exchangesRateService.getLastExchangeRates()).thenReturn(createExchangeRates());
        Mockito.when(exchangesRateService.convertSumToUSD(Mockito.any(ExchangeRates.class), Mockito.any(Transaction.class))).thenAnswer(invocation -> {
            Transaction transaction2 = invocation.getArgument(1);
            return transaction2.getSum().divide(BigDecimal.valueOf(102), 6, RoundingMode.HALF_UP);
        });

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(TransactionServiceImplTest::answer);

        Transaction newTransaction = transactionService.saveTransaction(transaction);
        Assertions.assertTrue(newTransaction.isLimitExceeded());

    }

    private Transaction createTransaction(BigDecimal sum) {
        Transaction transaction = new Transaction();
        transaction.setSum(sum);
        transaction.setAccountTo(2);
        transaction.setAccountFrom(1);
        transaction.setDatetime(ZonedDateTime.now());
        transaction.setExpenseCategory(ExpenseCategory.PRODUCT);
        transaction.setCurrencyShortname(CurrencyShortname.RUB);
        return transaction;
    }

    private Limit createLimit(BigDecimal limitSum) {
        Limit limit = new Limit();
        limit.setLimitSum(limitSum);
        limit.setAccountTo(2);
        limit.setLimitDatetime(ZonedDateTime.now());
        limit.setExpenseCategory(ExpenseCategory.PRODUCT);
        return limit;
    }

    private ExchangeRates createExchangeRates() {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setId(1L);
        exchangeRates.setDisclaimer("This is a disclaimer");
        exchangeRates.setLicense("MIT");
        exchangeRates.setTimestamp(LocalDateTime.now());
        exchangeRates.setDayOfReceivingInformation(LocalDate.now());
        exchangeRates.setBase("USD");
        exchangeRates.setRatesJson("{\"RUB\": 113, \"KZT\": 530}");

        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 113.0);
        rates.put("KZT", 530.0);
        exchangeRates.setRates(rates);
        return exchangeRates;
    }
}