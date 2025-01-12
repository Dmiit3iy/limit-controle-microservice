package org.dmit3ii.limitcontrolmicroservice.service;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;

import java.math.BigDecimal;

public interface ExchangesRateService {
    ExchangeRates saveExchangeRates(ExchangeRates exchangeRates);

    ExchangeRates getLastExchangeRates();

    BigDecimal convertSumToUSD(ExchangeRates exchangeRates, Transaction transaction);
}
