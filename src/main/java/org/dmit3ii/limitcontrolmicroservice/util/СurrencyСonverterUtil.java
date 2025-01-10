package org.dmit3ii.limitcontrolmicroservice.util;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class СurrencyСonverterUtil {

    private СurrencyСonverterUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Метод для перевода суммы транзакции из указанной в ней валюты в USD
     *
     * @param exchangeRates
     * @param transaction
     * @return
     */
    public static BigDecimal convertSumToUSD(ExchangeRates exchangeRates, Transaction transaction) {
        if (exchangeRates == null || transaction == null) {
            throw new IllegalArgumentException("Exchange rates or transaction cannot be null");
        }
        Double value = exchangeRates.getRates().get(transaction.getCurrencyShortname().name());
        BigDecimal usd = BigDecimal.valueOf(value);
        return transaction.getSum().multiply(usd).setScale(6, RoundingMode.HALF_UP);
    }
}
