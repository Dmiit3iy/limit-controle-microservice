package org.dmit3ii.limitcontrolmicroservice.service;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;

public interface ExchangesRateService {
    ExchangeRates saveExchangeRates(ExchangeRates exchangeRates);

    ExchangeRates getLastExchangeRates();
}
